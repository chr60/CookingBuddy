package edu.pitt.cs.cs1635.cookingbuddy;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class mLoginActivity extends MainActivity {


    public static final int VALID_PASSWORD_LENGTH = 5;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private EditText mEmailView;
    private EditText mPasswordView;
    private TextView mFeedBack;
    private TextView toRegister;
    private Button signup;


    private boolean isEmailValid(String email) {
        return VALID_EMAIL_ADDRESS_REGEX .matcher(email).find();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > VALID_PASSWORD_LENGTH;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlogin);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        toRegister = (TextView) findViewById(R.id.link_signup);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmailView.setError(null);
                mPasswordView.setError(null);

                // Store values at the time of the login attempt.
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                }
                else if (TextUtils.isEmpty(email)) {
                    mEmailView.setError(getString(R.string.error_field_required));

                } else if (!isEmailValid(email)) {
                    mEmailView.setError(getString(R.string.error_invalid_email));

                }
                else {
                    if (isValidLogin(email, password)){

                    }
                    else {
                        mFeedBack = (TextView) findViewById(R.id.login_feedback);
                        mFeedBack.setText("INVALID USERNAME OR PASSWORD.");
                        Log.d("email_pass", email + ":" + password);
                    }
                }
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActivity.this.setContentView(R.layout.activity_signup);
                signup = (Button) findViewById(R.id.btn_signup);
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validateRegistration()) {
                            setResult(RESULT_OK, null);
                            finish();
                        }
                    }
                });
            }
        });
        
    }


    public boolean validateRegistration() {

        EditText e = ((EditText)findViewById(R.id.input_email));
        EditText p = ((EditText)findViewById(R.id.input_password));
        EditText re = ((EditText)findViewById(R.id.input_reEnterPassword));

        e.setError(null);
        p.setError(null);
        re.setError(null);

        String email = e.getText().toString();
        String password =  p.getText().toString();
        String reEnterPassword = re.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e.setError(getString(R.string.error_invalid_email));
        }
        else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            p.setError(getString(R.string.error_invalid_password));
        }
        else if (TextUtils.isEmpty(email)) {
            e.setError(getString(R.string.error_field_required));

        } else if (!isEmailValid(email)) {
            e.setError(getString(R.string.error_invalid_email));
        }
        else if (!reEnterPassword.equals(password)){
            re.setError("Passwords do not match");
        }
        else {
            return true;
        }

        // TODO: Implement check for similar addresses
        return false;
    }

    public boolean isValidLogin(String e, String p) {
        return super.isValidPair(e, p);
    }

}
