package com.example.ourfarm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mqttkotlinsample.*
import kotlinx.android.synthetic.main.fragment_test.*
import org.eclipse.paho.client.mqttv3.*

class TestFragment : Fragment() {
    private lateinit var mqttClient : MQTTClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test, container, false)
//        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-humid")
//        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-lumi")
//        mqttClientLocal.subscribe("binhbuibksg0123/feeds/ourfarm-temp")

        return view
    }
//    private fun setMqttCallBack() {
//        mqttClientLocal.setCallback(object : MqttCallbackExtended {
//            override fun messageArrived(topic: String?, message: MqttMessage?) {
//                when(topic){
//                    "binhbuibksg0123/feeds/ourfarm-humid"->humidTxt.setText(message.toString())
//                    "binhbuibksg0123/feeds/ourfarm-lumi"->lightTxt.setText(message.toString())
//                    "binhbuibksg0123/feeds/ourfarm-temp"->temperTxt.setText(message.toString())
//                }
//                Log.d("messageArrived",topic!!)
//            }
//
//            override fun connectionLost(cause: Throwable?) {
//                Log.d("connectionLost",cause.toString())
//            }
//
//            override fun deliveryComplete(token: IMqttDeliveryToken?) {
//                Log.d("deliveryComplete",token.toString())
//            }
//
//            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
//                Log.d("connectComplete",reconnect.toString())
//            }
//        })
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments passed by ConnectFragment
        val serverURI = "tcp://io.adafruit.com:1883"
        val clientId = "42312346"
        val username = "binhbuibksg0123"
        val pwd = "aio_lOZw99nsUAWKqAzneXv35P86JZv4"

        // Check if passed arguments are valid
        if (serverURI != null &&
            clientId != null &&
            username != null &&
            pwd != null
        ) {
            // Open MQTT Broker communication
            mqttClient = MQTTClient(context, serverURI, clientId)

            // Connect and login to MQTT Broker
            mqttClient.connect(username,
                pwd,
                object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.d(this.javaClass.name, "Connection success")
                        if (mqttClient.isConnected()) {
                            mqttClient.subscribe("binhbuibksg0123/feeds/ourfarm-humid",
                                1,
                                object : IMqttActionListener {
                                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                                        val msg = "Subscribed to: binhbuibksg0123/feeds/ourfarm-humid"
                                        Log.d(this.javaClass.name, msg)

                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }

                                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                                        Log.d(this.javaClass.name, "Failed to subscribe: binhbuibksg0123/feeds/ourfarm-humid")
                                    }
                                })
                        } else {
                            Log.d(this.javaClass.name, "Impossible to subscribe, no server connected")
                        }
                        if (mqttClient.isConnected()) {
                            mqttClient.subscribe("binhbuibksg0123/feeds/ourfarm-lumi",
                                1,
                                object : IMqttActionListener {
                                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                                        val msg = "Subscribed to: binhbuibksg0123/feeds/ourfarm-lumi"
                                        Log.d(this.javaClass.name, msg)

                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }

                                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                                        Log.d(this.javaClass.name, "Failed to subscribe: binhbuibksg0123/feeds/ourfarm-lumi")
                                    }
                                })
                        } else {
                            Log.d(this.javaClass.name, "Impossible to subscribe, no server connected")
                        }
                        if (mqttClient.isConnected()) {
                            mqttClient.subscribe("binhbuibksg0123/feeds/ourfarm-temp",
                                1,
                                object : IMqttActionListener {
                                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                                        val msg = "Subscribed to: binhbuibksg0123/feeds/ourfarm-temp"
                                        Log.d(this.javaClass.name, msg)

                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }

                                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                                        Log.d(this.javaClass.name, "Failed to subscribe: binhbuibksg0123/feeds/ourfarm-temp")
                                    }
                                })
                        } else {
                            Log.d(this.javaClass.name, "Impossible to subscribe, no server connected")
                        }
                        Toast.makeText(context, "MQTT Connection success", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.d(this.javaClass.name, "Connection failure: ${exception.toString()}")

                        Toast.makeText(
                            context,
                            "MQTT Connection fails: ${exception.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                },
                object : MqttCallback {
                    override fun messageArrived(topic: String?, message: MqttMessage?) {
                        val msg = "Receive message: ${message.toString()} from topic: $topic"
                        Log.d("testbinh", msg)
                        when(topic){
                            "binhbuibksg0123/feeds/ourfarm-humid"->humidTxt.setText(message.toString())
                            "binhbuibksg0123/feeds/ourfarm-lumi"->lightTxt.setText(message.toString())
                            "binhbuibksg0123/feeds/ourfarm-temp"->temperTxt.setText(message.toString())
                        }
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }

                    override fun connectionLost(cause: Throwable?) {
                        Log.d(this.javaClass.name, "Connection lost ${cause.toString()}")
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken?) {
                        Log.d(this.javaClass.name, "Delivery complete")
                    }
                })
        } else {
        }

    }
}