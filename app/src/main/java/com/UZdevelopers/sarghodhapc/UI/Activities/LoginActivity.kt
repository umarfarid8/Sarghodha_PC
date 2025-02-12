package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.ViewModels.AuthViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= AuthViewModel()
//        viewModel.checkUser()
        progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait while we check your credentials...")
        progressDialog.setCancelable(false)



        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                progressDialog.dismiss()
                if (it!=null){
                    Toast.makeText(this@LoginActivity,it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.currentUser.collect{
                if (it!=null){
                    progressDialog.dismiss()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                    viewModel.loadUser()
                    finish()
                }
            }
        }
        if( FirebaseAuth.getInstance().currentUser != null) {
            progressDialog.setMessage("Loading Profile...")
            progressDialog.show()
            viewModel.loadUser()
        }

//        AuthRepository().signup("Zhair Ahmad", " umerfarid034@gmail.com ", "03444390314", "123456")

        binding.forget.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
            finish()
        }

        binding.loginbtn.setOnClickListener {
            val email=binding.name.editText?.text.toString()
            val password=binding.password.editText?.text.toString()

            Log.i("signT", email)
            Log.i("signT", password)

            if(!email.contains("@")){
                Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length<6){
                Toast.makeText(this,"Password must be at least 6 characters",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressDialog.show()

            viewModel.login(email,password)
        }

    }
}