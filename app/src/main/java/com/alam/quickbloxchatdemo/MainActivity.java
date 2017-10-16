package com.alam.quickbloxchatdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alam.quickbloxchatdemo.utils.AlertDialogManager;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBPrivateChat;
import com.quickblox.chat.QBPrivateChatManager;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBMessageListener;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.edtTextUserName)
    EditText edtTextUserName;

    @InjectView(R.id.edtTextUserPass)
    EditText edtTextUserPass;

    @InjectView(R.id.edtTextOpponentId)
    EditText edtTextOpponentId;

    int opponentId;
    QBPrivateChatManager privateChatManager;
    QBMessageListener<QBPrivateChat> privateChatMessageListener;
    QBChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        chatService = QBChatService.getInstance();
        registerQbChatListeners();

        privateChatMessageListener = new QBMessageListener<QBPrivateChat>() {
            @Override
            public void processMessage(QBPrivateChat privateChat, final QBChatMessage chatMessage) {
                //Log.e("Chat Message", "" + chatMessage.getBody());
                //chatAdapter.updateList(chatMessage);

                // chatMessage.getBody();


               /* requestBuilder.setSkip(skipRecords = 0);
                if (isActivityForeground) {
                    loadDialogsFromQbInUiThread(true);
                }*/
            }


            @Override
            public void processError(QBPrivateChat privateChat, QBChatException error, QBChatMessage originMessage) {
                error.printStackTrace();
                Progress.stop();
            }
        };

        //registerQbChatListeners();

    }

    @OnClick(R.id.btnLogin)
    public void btnLoginClick(){
        String password=edtTextUserPass.getText().toString();
        String opponentId=edtTextOpponentId.getText().toString();
        loginUserOnQuikBlox(edtTextUserName.getText().toString(), edtTextUserPass.getText().toString());
    }

    @OnClick(R.id.btn_register)
    public void btn_registerClick(){
        String username=edtTextUserName.getText().toString();
        registerUserOnQuikBlox(username+"@gmail.com", "username", username+"1234");//emailid, name, username
    }



    // login to quickblox
    private void  loginUserOnQuikBlox(final String username, final String password) {
        Progress.start(this);

        final QBUser user = new QBUser(username, password);

        QBUsers.signIn(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle params) {
                Log.e("signIn", "onSuccess");
                Progress.stop();

                UserSession userSession = new UserSession(MainActivity.this);
                userSession.setUserID(user.getLogin());
                userSession.setQuikBloxID(user.getId().toString());

                //QBPrivateChat privateChat = privateChatManager.getChat(opponentId);
               /* if (privateChat == null) {
                    privateChat = privateChatManager.createChat(opponentId, privateChatMessageListener);
                }

                Log.e("Dialog id is -----", "" + privateChat.getDialogId());
*/
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra(Const.KEY_OPPONENT_ID, Integer.parseInt(edtTextOpponentId.getText().toString()));
                intent.putExtra(Const.KEY_NAME, "Bb");
                intent.putExtra(Const.KEY_DIALOG_ID, "59d921dca28f9a0ab0ce1407");
                startActivity(intent);
            }

            @Override
            public void onError(QBResponseException errors) {
                errors.printStackTrace();
                Progress.stop();
            }
        });


    }


    //Sign Up Quik Blox
    private void registerUserOnQuikBlox(String email, String name, String userName) {

        Progress.start(this);

        final QBUser user = new QBUser(userName, "Braintech1234");
        user.setFullName(name);
        user.setEmail(email);


        QBUsers.signUp(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                Log.e("Inside", "signUp onSuccess " + user.getId());

                Progress.stop();
            }

            @Override
            public void onError(QBResponseException errors) {
                Log.e("Inside", "signUp onError");
                errors.printStackTrace();

                Progress.stop();
            }
        });
    }

    private void registerQbChatListeners() {
        privateChatManager = chatService.getPrivateChatManager();
        //QBGroupChatManager groupChatManager = QBChatService.getInstance().getGroupChatManager();
        if (privateChatManager != null) {
            //privateChatManager.addPrivateChatManagerListener(privateChatManagerListener);


            new CreateDialog().execute(privateChatManager);

        }
    }

    private class CreateDialog extends AsyncTask<QBPrivateChatManager, String, QBPrivateChatManager> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected QBPrivateChatManager doInBackground(QBPrivateChatManager... qbPrivateChatManagers) {


            return qbPrivateChatManagers[0];
        }

        @Override
        protected void onPostExecute(final QBPrivateChatManager s) {
            super.onPostExecute(s);

            s.createDialog(opponentId, new QBEntityCallback<QBDialog>() {
                @Override
                public void onSuccess(QBDialog dialog, Bundle args) {
                    Log.d("CreateDialog", "onSuccess");
                    // dialogId = dialog.getDialogId();
                    //chatInPrivateChat(s, false);
                }

                @Override
                public void onError(QBResponseException errors) {
                    Log.d("CreateDialog", "onError " + errors.getMessage());
                    //Progress.stop();
                    // AlertDialogManager.sAlertFinish(ChatActivity.this, getString(R.string.chat_not_availble));
                    errors.printStackTrace();
                }
            });
        }
    }



}
