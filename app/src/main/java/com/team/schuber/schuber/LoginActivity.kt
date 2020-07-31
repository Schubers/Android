package com.team.schuber.schuber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.davidmiguel.numberkeyboard.NumberKeyboardListener
import com.team.schuber.schuber.model.LoginRequestModel
import com.team.schuber.schuber.model.LoginResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_first_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity: AppCompatActivity(), NumberKeyboardListener {

    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_password)

        keyboard.visibility = View.GONE

        password_message.text = "비밀번호를 입력해주세요"

        new_account_account_tv.setOnClickListener {
            startActivity(Intent(this, VerifyActivity::class.java))
        }

        keyboard.setListener(this)

        input_button.setOnClickListener {
            input_button.onLoad("로딩 중")

            Network.getApi().login(
                LoginRequestModel(
                    SharedPrefStorage(this).getPhone(), password, SharedPrefStorage(this).getKey()
                )
            ).enqueue(object: Callback<LoginResponseModel> {
                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    input_button.onError("에러")
                }

                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            input_button.onSuccess("로그인 성공")
                            Handler().postDelayed(
                                {
                                    SharedPrefStorage(this@LoginActivity).saveToken(response.body()!!.accessToken, true)
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                    finish()
                                }, 2000
                            )
                        }
                        else -> {
                            input_button.onError("에러")
                        }
                    }
                }
            })
        }
    }

    fun focusNum(view: View) {
        keyboard.visibility = View.VISIBLE
    }

    fun hideKeyboard(view: View) {
        keyboard.visibility = View.GONE
    }

    override fun onNumberClicked(number: Int) {

        if (password.length <= 5) {
            password += number
        }

        when (password.length) {
            1 -> password_first.background = getDrawable(R.drawable.main_circle)
            2 -> password_second.background = getDrawable(R.drawable.main_circle)
            3 -> password_third.background = getDrawable(R.drawable.main_circle)
            4 -> password_fourth.background = getDrawable(R.drawable.main_circle)
            5 -> {
                password_fifth.background = getDrawable(R.drawable.main_circle)
                keyboard.visibility = View.GONE
            }
        }

    }

    override fun onLeftAuxButtonClicked() {

    }

    override fun onRightAuxButtonClicked() {
        when(password.length) {
            1 -> {
                password_first.background = getDrawable(R.drawable.gray_circle)
                keyboard.visibility = View.GONE
            }
            2 -> password_second.background = getDrawable(R.drawable.gray_circle)
            3 -> password_third.background = getDrawable(R.drawable.gray_circle)
            4 -> password_fourth.background = getDrawable(R.drawable.gray_circle)
            5 -> password_fifth.background = getDrawable(R.drawable.gray_circle)
        }
        password = password.dropLast(1)
    }

}