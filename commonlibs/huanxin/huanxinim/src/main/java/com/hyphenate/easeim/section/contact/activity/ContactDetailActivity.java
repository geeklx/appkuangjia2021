package com.hyphenate.easeim.section.contact.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMUserInfo;
import com.hyphenate.easecallkit.EaseCallKit;
import com.hyphenate.easecallkit.base.EaseCallType;

import com.hyphenate.easeim.DemoHelper;
import com.hyphenate.easeim.R;
import com.hyphenate.easeim.common.constant.DemoConstant;
import com.hyphenate.easeim.common.db.DemoDbHelper;
import com.hyphenate.easeim.common.db.entity.EmUserEntity;
import com.hyphenate.easeim.common.interfaceOrImplement.OnResourceParseCallback;
import com.hyphenate.easeim.common.livedatas.LiveDataBus;
import com.hyphenate.easeim.section.base.BaseInitActivity;
import com.hyphenate.easeim.section.chat.activity.ChatActivity;
import com.hyphenate.easeim.section.dialog.DemoDialogFragment;
import com.hyphenate.easeim.section.dialog.SimpleDialogFragment;
import com.hyphenate.easeim.section.contact.viewmodels.AddContactViewModel;
import com.hyphenate.easeim.section.contact.viewmodels.ContactBlackViewModel;
import com.hyphenate.easeim.section.contact.viewmodels.ContactDetailViewModel;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseImageView;
import com.hyphenate.easeui.widget.EaseTitleBar;

import java.util.List;
import java.util.Map;

public class ContactDetailActivity extends BaseInitActivity implements EaseTitleBar.OnBackPressListener, View.OnClickListener {
    private EaseTitleBar mEaseTitleBar;
    private EaseImageView mAvatarUser;
    private TextView mTvName;
    private TextView mTvNote;
    private TextView mBtnChat;
    private TextView mBtnVoice;
    private TextView mBtnVideo;
    private TextView mBtnAddContact;
    private TextView mBtnRemoveBlack;
    private Group mGroupFriend;

    private EaseUser mUser;
    private boolean mIsFriend;
    private boolean mIsBlack;
    private ContactDetailViewModel viewModel;
    private AddContactViewModel addContactViewModel;
    private ContactBlackViewModel blackViewModel;
    private EMUserInfo userInfo;
    private LiveDataBus contactChangeLiveData;

    public static void actionStart(Context context, EaseUser user) {
        Intent intent = new Intent(context, ContactDetailActivity.class);
        intent.putExtra("user", (EaseUser)user);
        if(user.getContact() == 0){
            intent.putExtra("isFriend", true);
        }else{
            intent.putExtra("isFriend", false);
        }

        context.startActivity(intent);
    }

    public static void actionStart(Context context, EaseUser user, boolean isFriend) {
        Intent intent = new Intent(context, ContactDetailActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("isFriend", isFriend);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_friends_contact_detail;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return mIsFriend && !mIsBlack;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_friends_contact_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_detail_delete) {
            showDeleteDialog(mUser);
        } else if (itemId == R.id.action_add_black) {
            viewModel.addUserToBlackList(mUser.getUsername(), false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initIntent(Intent intent) {
        super.initIntent(intent);
        mUser = (EaseUser)getIntent().getSerializableExtra("user");
        mIsFriend = getIntent().getBooleanExtra("isFriend", true);
        if(!mIsFriend) {
            List<String> users = null;
            if(DemoDbHelper.getInstance(mContext).getUserDao() != null) {
                users = DemoDbHelper.getInstance(mContext).getUserDao().loadContactUsers();
            }
            mIsFriend = users != null && users.contains(mUser.getUsername());
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mEaseTitleBar = findViewById(R.id.title_bar_contact_detail);
        mAvatarUser = findViewById(R.id.avatar_user);
        mTvName = findViewById(R.id.tv_name);
        mTvNote = findViewById(R.id.tv_note);
        mBtnChat = findViewById(R.id.btn_chat);
        mBtnVoice = findViewById(R.id.btn_voice);
        mBtnVideo = findViewById(R.id.btn_video);
        mBtnAddContact = findViewById(R.id.btn_add_contact);
        mGroupFriend = findViewById(R.id.group_friend);
        mBtnRemoveBlack = findViewById(R.id.btn_remove_black);

        if(mIsFriend) {
            mGroupFriend.setVisibility(View.VISIBLE);
            mBtnAddContact.setVisibility(View.GONE);
            EaseUser user = DemoHelper.getInstance().getModel().getContactList().get(mUser.getUsername());
            if(user != null && user.getContact() == 1) {
                mIsBlack = true;
                //如果在黑名单中
                mGroupFriend.setVisibility(View.GONE);
                mBtnRemoveBlack.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            }
        }else {
            mGroupFriend.setVisibility(View.GONE);
            mBtnAddContact.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mEaseTitleBar.setOnBackPressListener(this);
        mTvNote.setOnClickListener(this);
        mBtnChat.setOnClickListener(this);
        mBtnVoice.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnAddContact.setOnClickListener(this);
        mBtnRemoveBlack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        contactChangeLiveData = LiveDataBus.get();
        getDetailInfo();
        viewModel = new ViewModelProvider(this).get(ContactDetailViewModel.class);
        viewModel.blackObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.CONTACT_CHANGE).postValue(EaseEvent.create(DemoConstant.CONTACT_CHANGE, EaseEvent.TYPE.CONTACT));
                    finish();
                }
            });
        });
        viewModel.deleteObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.CONTACT_CHANGE).postValue(EaseEvent.create(DemoConstant.CONTACT_CHANGE, EaseEvent.TYPE.CONTACT));
                    finish();
                }
            });
        });

        addContactViewModel = new ViewModelProvider(mContext).get(AddContactViewModel.class);
        addContactViewModel.getAddContact().observe(mContext, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    if(data) {
                        showToast(getResources().getString(R.string.em_add_contact_send_successful));
                        mBtnAddContact.setEnabled(false);
                    }
                }
            });
        });

        blackViewModel = new ViewModelProvider(this).get(ContactBlackViewModel.class);

        blackViewModel.resultObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.CONTACT_CHANGE).postValue(EaseEvent.create(DemoConstant.CONTACT_CHANGE, EaseEvent.TYPE.CONTACT));
                    finish();
                }
            });
        });
    }

    private void showDeleteDialog(EaseUser user) {
        new SimpleDialogFragment.Builder(mContext)
                .setTitle(R.string.ease_friends_delete_contact_hint)
                .setOnConfirmClickListener(new DemoDialogFragment.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(View view) {
                        viewModel.deleteContact(user.getUsername());
                    }
                })
                .showCancelButton(true)
                .show();
    }

    @Override
    public void onBackPress(View view) {
        onBackPressed();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_note) {
            showToast("跳转到备注设置");
        } else if (id == R.id.btn_chat) {
            ChatActivity.actionStart(mContext, mUser.getUsername(), EaseConstant.CHATTYPE_SINGLE);
        } else if (id == R.id.btn_voice) {
            EaseCallKit.getInstance().startSingleCall(EaseCallType.SINGLE_VOICE_CALL, mUser.getUsername(), null);
        } else if (id == R.id.btn_video) {
            EaseCallKit.getInstance().startSingleCall(EaseCallType.SINGLE_VIDEO_CALL, mUser.getUsername(), null);
        } else if (id == R.id.btn_add_contact) {
            addContactViewModel.addContact(mUser.getUsername(), getResources().getString(R.string.em_add_contact_add_a_friend));
        } else if (id == R.id.btn_remove_black) {//从黑名单中移除
            removeBlack();
        }
    }

    private void removeBlack() {
        new SimpleDialogFragment.Builder(mContext)
                .setTitle(R.string.em_friends_move_out_the_blacklist_hint)
                .setOnConfirmClickListener(new DemoDialogFragment.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(View view) {
                        blackViewModel.removeUserFromBlackList(mUser.getUsername());
                    }
                })
                .showCancelButton(true)
                .show();
    }

    private void getDetailInfo(){
        String[] userId = new String[1];
        userId[0] = mUser.getUsername();
        boolean isSelf = mUser.getUsername().contains(EMClient.getInstance().getCurrentUser());
        if(isSelf){
            userId[0] = EMClient.getInstance().getCurrentUser();
        }
        EMClient.getInstance().userInfoManager().fetchUserInfoByUserId(userId, new EMValueCallBack<Map<String, EMUserInfo>>() {
            @Override
            public void onSuccess(Map<String, EMUserInfo> userInfos) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if(isSelf){
                            userInfo = userInfos.get(EMClient.getInstance().getCurrentUser());
                        }else{
                            userInfo = userInfos.get(mUser.getUsername());
                        }

                        if (userInfo != null && userInfo.getNickName() != null && userInfo.getNickName().length() > 0) {
                            mTvName.setText(userInfo.getNickName());
                        }else{
                            mTvName.setText(mUser.getUsername());
                        }
                        if (userInfo != null && userInfo.getAvatarUrl() != null && userInfo.getAvatarUrl().length() > 0) {
                            Glide.with(mContext).load(userInfo.getAvatarUrl()).placeholder(R.drawable.em_login_logo).into(mAvatarUser);
                        }
                        //更新本地数据库
                        warpEMUserInfo(userInfo);
                    }
                });
            }

            @Override
            public void onError(int error, String errorMsg) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        mTvName.setText(mUser.getUsername());
                    }
                });
            }
        });
    }

    private void warpEMUserInfo(EMUserInfo userInfo){
        if(userInfo != null && mUser != null){
            EmUserEntity userEntity = new EmUserEntity();
            userEntity.setUsername(mUser.getUsername());
            userEntity.setNickname(userInfo.getNickName());
            userEntity.setEmail(userInfo.getEmail());
            userEntity.setAvatar(userInfo.getAvatarUrl());
            userEntity.setBirth(userInfo.getBirth());
            userEntity.setGender(userInfo.getGender());
            userEntity.setExt(userInfo.getExt());
            userEntity.setSign(userInfo.getSignature());
            EaseCommonUtils.setUserInitialLetter(userEntity);
            userEntity.setContact(mUser.getContact());

            //更新本地数据库信息
            DemoHelper.getInstance().update(userEntity);

            //更新本地联系人列表
            DemoHelper.getInstance().updateContactList();
            EaseEvent event = EaseEvent.create(DemoConstant.CONTACT_UPDATE, EaseEvent.TYPE.CONTACT);
            event.message = mUser.getUsername();
            //发送联系人更新事件
            contactChangeLiveData.with(DemoConstant.CONTACT_UPDATE).postValue(event);
        }
    }
}
