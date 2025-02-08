package com.UZdevelopers.UI.Activities

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.ViewModels.AuthViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityResetPasswordBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var binding: ActivityResetPasswordBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= AuthViewModel()

        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Please wait while...")
        progressDialog.setCancelable(false)

        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                progressDialog.dismiss()
                if (it!=null){
                    Toast.makeText(this@ResetPasswordActivity,it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.resetResponse.collect{
                progressDialog.dismiss()
                if (it!=null){
                    val builder= MaterialAlertDialogBuilder(this@ResetPasswordActivity)
                    builder.setMessage("We have sent you a password reset email, check your inbox and click the link to reset your password")
                    builder.setCancelable(false)
                    builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                        finish()
                    })
                    builder.show()
                }
            }
        }

        binding.loginBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.restPasswordbtn.setOnClickListener {
            val email=binding.forgetPassword.editText?.text.toString()

            if(!email.contains("@")){
                Toast.makeText(this,"Invalid Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressDialog.show()

            viewModel.resetPassword(email)

        }


    }
}