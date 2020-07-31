package com.team.schuber.schuber

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.davidmiguel.numberkeyboard.NumberKeyboardListener
import com.team.schuber.schuber.model.RegisterRequestModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_first_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity: AppCompatActivity(), NumberKeyboardListener {

    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_password)

        keyboard.visibility = View.GONE

        password_message.text = "초기 비밀번호를\n설정해주세요."
        input_button.text = "설정하기"
        new_account_desc_tv.visibility = View.GONE
        new_account_account_tv.visibility = View.GONE

        keyboard.setListener(this)

        input_button.setOnClickListener {
            input_button.onLoad("로딩 중")

            Network.getApi().register(
                RegisterRequestModel(
                    SharedPrefStorage(this).getPhone(), password, SharedPrefStorage(this).getKey()
                )
            ).enqueue(object: Callback<Unit> {

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    input_button.onError("에러")
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    when(response.code()) {
                        201 -> {
                            input_button.onSuccess("회원가입 성공")
                            Handler().postDelayed(
                                {
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