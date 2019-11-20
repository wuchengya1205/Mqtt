package com.itsync.mqtt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.google.gson.Gson;
import com.itsync.bean.DataBean;
import com.itsync.bean.NewDataBean;
import com.itsync.bean.NewDataBean.ResultsBean;
import com.itsync.bean.NewDataBean.ResultsBean.AttributesBean;
import com.itsync.db.DbBusiness;
import com.itsync.util.CodeUtils;

public class Paho {

	private static MqttClient client;
	private static int TotalNum = 0;
	private static int time = 0;

	public static void main(String[] args) throws MqttException, InterruptedException {
		MemoryPersistence memoryPersistence = new MemoryPersistence();

		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName("admin");
		options.setPassword("Senscape".toCharArray());
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		options.setKeepAliveInterval(20);
		client = new MqttClient("tcp://10.1.1.204:1883", "java", memoryPersistence);
		client.setCallback(mqttCallbackExtended);
		client.connect(options);

	}

	private static MqttCallbackExtended mqttCallbackExtended = new MqttCallbackExtended() {

		@Override
		public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
			// 收到消息
			System.out.println("receive 收到消息");
			String string = new String(arg1.getPayload(), "UTF-8");
			NewDataBean fromJson = new Gson().fromJson(string, NewDataBean.class);
			List<ResultsBean> results = fromJson.getResults();
			AttributesBean attributes = results.get(0).getAttributes();
			int currentNum = attributes.getStat_age0() + attributes.getStat_age1() + attributes.getStat_age2()
					+ attributes.getStat_age3() + attributes.getStat_age4();
			if (currentNum != TotalNum) {
				TotalNum = currentNum;
				// 删除所有的数据
				DbBusiness.deleteAll();
				// 添加最新的数据
				DbBusiness.addData(attributes);
				if (results.get(0).getU_id() > 0) {
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String format = sdf.format(d);
					String imagePath = System.getProperty("user.dir") + "\\images\\" + format + "_" + (time++) + ".jpg";
					ResultsBean rb = results.get(0);
					String db_image = rb.getDb_image();
					if (db_image.contains(",")) {
						String[] split = db_image.split(",");
						CodeUtils.GenerateImage(split[1], imagePath);
					}
					DataBean dataBean = new DataBean(imagePath, rb.getName(), rb.getType(), rb.getPerson_id(),
							rb.getAge(), rb.getGender(), rb.getTitle());
					DbBusiness.addPersonInfo(dataBean);

				}
			}

		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken arg0) {
			// 交付完成
			System.out.println("[isComplete]:" + arg0.isComplete() + "       " + arg0.getTopics());

		}

		@Override
		public void connectionLost(Throwable arg0) {
			// 连接丢失
			System.out.println("connectionLost:" + arg0.getMessage());

		}

		@Override
		public void connectComplete(boolean arg0, String arg1) {
			// 连接成功,上传客户端所有订阅关系
			System.out.println("connectComplete:" + arg0 + "连接地址:" + arg1);
			// 订阅
			int[] Qos = { 1 };
			String[] topic1 = { "/recog/senscape" };
			try {
				client.subscribe(topic1, Qos);
				System.out.println("订阅了");
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

}
