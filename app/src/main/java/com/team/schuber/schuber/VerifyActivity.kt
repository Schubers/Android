package com.team.schuber.schuber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.team.schuber.schuber.model.VerifyCheckRequestModel
import com.team.schuber.schuber.model.VerifyCheckResponseModel
import com.team.schuber.schuber.model.VerifyRequestModel
import com.team.schuber.schuber.model.VerifyResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_identity_verification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyActivity : AppCompatActivity() {

    var isMessaged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity_verification)

        identity_message.text = "전화번호를\n입력해주세요."

        identity_verify_button.setOnClickListener {
            identity_verify_button.onLoad("로딩 중")

            if (isMessaged) {
                Network.getApi().checkVerification(
                    SharedPrefStorage(this).getKey(),
                    VerifyCheckRequestModel(identity_code_content.text.toString().toInt())
                ).enqueue(object: Callback<VerifyCheckResponseModel> {

                    override fun onFailure(call: Call<VerifyCheckResponseModel>, t: Throwable) {
                        identity_verify_button.onError("에러")
                    }

                    override fun onResponse(
                        call: Call<VerifyCheckResponseModel>,
                        response: Response<VerifyCheckResponseModel>
                    ) {
                        when(response.code()) {
                            200 -> {
                                identity_verify_button.onSuccess("인증 성공")
                                Handler().postDelayed(
                                    {
                                        startActivity(Intent(this@VerifyActivity, RegisterActivity::class.java))
                                        finish()
                                    }, 2000
                                )
                            }
                            else -> {
                                identity_verify_button.onError("에러")
                            }
                        }
                    }
                })
            } else {
                sendCode()
            }
        }

        resend_send_tv.setOnClickListener {
            sendCode()
            Toast.makeText(this, "다시 전송하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendCode() {
        isMessaged = false
        Network.getApi().sendVerification(
            VerifyRequestModel(
                "+82" + identity_phone_content.text.toString()
                    .replace("-", "")
                    .drop(1))
        ).enqueue(object: Callback<VerifyResponseModel> {

            override fun onFailure(call: Call<VerifyResponseModel>, t: Throwable) {
                identity_verify_button.onError("에러")
            }

            override fun onResponse(
                call: Call<VerifyResponseModel>,
                response: Response<VerifyResponseModel>
            ) {
                when(response.code()) {
                    201 -> {
                        identity_message.text = "회원님의 연락처로\n인증번호를 발송했습니다."
                        identity_verify_button.text = "인증하기"
                        isMessaged = true
                        identity_verify_button.isClickable = true

                        SharedPrefStorage(this@VerifyActivity).savePhone(
                            "+82" + identity_phone_content.text.toString()
                                .replace("-", "")
                                .drop(1)
                        )
                        SharedPrefStorage(this@VerifyActivity).saveKey(response.body()!!.verificationId)
                    }
                    else -> {
                        identity_verify_button.onError("에러")
                    }
                }
            }
        })
    }

}