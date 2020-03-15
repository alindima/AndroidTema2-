package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema2.Db.Entity.User;
import com.example.tema2.Db.Repository.OnDeleteUserActionListener;
import com.example.tema2.Db.Repository.OnGetUsersActionListener;
import com.example.tema2.Db.Repository.OnUserRepositoryActionListener;
import com.example.tema2.Db.Repository.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserRepository mUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserRepository = new UserRepository(getApplicationContext());

        setContentView(R.layout.activity_main);

        setTitle("Users");

    }

    public void onAdd(View view) {
        final User user = new User();
        final EditText nameEdit = findViewById(R.id.name);
        final EditText gradeEdit = findViewById(R.id.grade);

        user.name = nameEdit.getText().toString();

        if(user.name.isEmpty()){
            return;
        }

        try{
            user.grade = Integer.parseInt(gradeEdit.getText().toString());
        }catch(NumberFormatException e){
            return;
        };

        mUserRepository.insertTask(user, new OnUserRepositoryActionListener() {
            @Override
            public void actionSuccess() {
                nameEdit.setText("");
                gradeEdit.setText("");

                mUserRepository.getUsersTask(new OnGetUsersActionListener() {
                    @Override
                    public void actionSuccess(List<User> users) {
                        for (User user : users){
                            Log.println(Log.DEBUG, "users", user.name + " " + user.grade);
                        }
                    }
                });
            }
        });
    }

    public void onRemove(View view) {
        final EditText nameEdit = findViewById(R.id.name);

        if(nameEdit.getText().toString().isEmpty()){
            return;
        }

        mUserRepository.deleteUsersTask(nameEdit.getText().toString(), new OnDeleteUserActionListener(){
            @Override
            public void actionSuccess(Boolean result) {
                nameEdit.setText("");

                if(result){
                    mUserRepository.getUsersTask(new OnGetUsersActionListener() {
                        @Override
                        public void actionSuccess(List<User> users) {
                            for (User user : users){
                                Log.println(Log.DEBUG, "users", user.name);
                            }
                        }
                    });
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No user matches this name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
