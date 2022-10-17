package com.example.ourfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class DashboardActivity : AppCompatActivity() {
    val mqttClientLocal by lazy{
        MqttClientHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-humid")
        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-lumi")
        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-temp")
    }
        private fun setMqttCallBack() {
        mqttClientLocal.setCallback(object : MqttCallbackExtended {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                when(topic){
                    "binhbuibksg0123/feeds/ourfarm-humid"->humidTxt.setText(message.toString())
                    "binhbuibksg0123/feeds/ourfarm-lumi"->lightTxt.setText(message.toString())
                    "binhbuibksg0123/feeds/ourfarm-temp"->temperTxt.setText(message.toString())
                }
                Log.d("messageArrived",topic!!)
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d("connectionLost",cause.toString())
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("deliveryComplete",token.toString())
            }

            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                Log.d("connectComplete",reconnect.toString())
            }
        })
    }
}