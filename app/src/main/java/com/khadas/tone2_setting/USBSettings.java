package com.khadas.tone2_setting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class USBSettings extends Activity{
	private static final String TAG = "USBSettings";
	private Context mContext;

	TextView read_gpio_tv_35;
	TextView read_gpio_tv_37;

	Button write_gpio_btn;

	Button release_gpio_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpio_main);
		mContext = this;

		read_gpio_tv_35 = (TextView)findViewById(R.id.read_gpio_tv_35);
		read_gpio_tv_37 = (TextView)findViewById(R.id.read_gpio_tv_37);

		write_gpio_btn = (Button)findViewById(R.id.write_gpio_btn);

		release_gpio_btn = (Button)findViewById(R.id.release_gpio_btn);

		write_gpio_btn.setOnClickListener(ocl);
		release_gpio_btn.setOnClickListener(ocl);
	}

	OnClickListener ocl =new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			String productName = android.os.Build.PRODUCT;
			Log.d(TAG, "Product Name: " + productName);
			if (arg0.getId() == R.id.write_gpio_btn) {
				int value_35 = -1,value_37 = -1;
				try {
					// Get root privileges
					Process process_open = Runtime.getRuntime().exec("su");
					DataOutputStream os = new DataOutputStream(process_open.getOutputStream());
					if (productName.equals("kvim1s")) {
						// Apply for GPIO
						os.writeBytes("echo " + 503 + " > /sys/class/gpio/export\n");
						os.writeBytes("echo " + 508 + " > /sys/class/gpio/export\n");
						// Set output mode
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 503 + "/direction\n");
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 508 + "/direction\n");
						// I2S channel
						os.writeBytes("echo 0 > /sys/class/gpio/gpio" + 503 + "/value\n"); //(PIN35)
						os.writeBytes("echo 1 > /sys/class/gpio/gpio" + 508 + "/value\n"); //(PIN37)

						os.flush();  // Flush the output stream
						os.close();  // Close output stream
						process_open.waitFor();  // Wait for command execution to complete

						// Read PIN35 GPIO
						Process process_35 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 503 + "/value");
						InputStream is_35 = process_35.getInputStream();
						StringBuilder stringBuilder_35 = new StringBuilder();
						int c_35;
						while ((c_35 = is_35.read()) != -1) {
							stringBuilder_35.append((char) c_35);
						}
						value_35 = Integer.parseInt(stringBuilder_35.toString().trim());
						// Display GPIO value
						read_gpio_tv_35.setText("VIM1S GPIO 503 the value is: " + value_35); // Display value in text view next to button

						// Read PIN37 GPIO
						Process process_37 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 508 + "/value");
						InputStream is_37 = process_37.getInputStream();
						StringBuilder stringBuilder_37 = new StringBuilder();
						int c_37;
						while ((c_37 = is_37.read()) != -1) {
							stringBuilder_37.append((char) c_37);
						}
						value_37 = Integer.parseInt(stringBuilder_37.toString().trim());
						// Display GPIO value
						read_gpio_tv_37.setText("VIM1S GPIO 508 the value is: " + value_37); // Display value in text view next to button
					}
					if (productName.equals("kvim3") || productName.equals("kvim3l")) {
						// Apply for GPIO
						os.writeBytes("echo " + 432 + " > /sys/class/gpio/export\n");
						os.writeBytes("echo " + 431 + " > /sys/class/gpio/export\n");
						// Set output mode
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 432 + "/direction\n");
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 431 + "/direction\n");
						// I2S channel
						os.writeBytes("echo 0 > /sys/class/gpio/gpio" + 432 + "/value\n"); //(PIN35)
						os.writeBytes("echo 1 > /sys/class/gpio/gpio" + 431 + "/value\n"); //(PIN37)

						os.flush();  // Flush the output stream
						os.close();  // Close output stream
						process_open.waitFor();  // Wait for command execution to complete

						// Read PIN35 GPIO
						Process process_35 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 432 + "/value");
						InputStream is_35 = process_35.getInputStream();
						StringBuilder stringBuilder_35 = new StringBuilder();
						int c_35;
						while ((c_35 = is_35.read()) != -1) {
							stringBuilder_35.append((char) c_35);
						}
						value_35 = Integer.parseInt(stringBuilder_35.toString().trim());
						// Display GPIO value
						if (productName.equals("kvim3")) {
							read_gpio_tv_35.setText("VIM3 GPIO 432 the value is: " + value_35); // Display value in text view next to button
						}
						if (productName.equals("kvim3l")) {
							read_gpio_tv_35.setText("VIM3L GPIO 432 the value is: " + value_35); // Display value in text view next to button
						}

						// Read PIN37 GPIO
						Process process_37 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 431 + "/value");
						InputStream is_37 = process_37.getInputStream();
						StringBuilder stringBuilder_37 = new StringBuilder();
						int c_37;
						while ((c_37 = is_37.read()) != -1) {
							stringBuilder_37.append((char) c_37);
						}
						value_37 = Integer.parseInt(stringBuilder_37.toString().trim());
						// Display GPIO value
						if (productName.equals("kvim3")) {
							read_gpio_tv_37.setText("VIM3 GPIO 431 the value is: " + value_37); // Display value in text view next to button
						}
						if (productName.equals("kvim3l")) {
							read_gpio_tv_37.setText("VIM3L GPIO 431 the value is: " + value_37); // Display value in text view next to button
						}
					}
					if (productName.equals("kvim4")) {
						// Apply for GPIO
						os.writeBytes("echo " + 492 + " > /sys/class/gpio/export\n");
						os.writeBytes("echo " + 465 + " > /sys/class/gpio/export\n");
						// Set output mode
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 492 + "/direction\n");
						os.writeBytes("echo out > /sys/class/gpio/gpio" + 465 + "/direction\n");
						// I2S channel
						os.writeBytes("echo 0 > /sys/class/gpio/gpio" + 492 + "/value\n"); //(PIN35)
						os.writeBytes("echo 1 > /sys/class/gpio/gpio" + 465 + "/value\n"); //(PIN37)

						os.flush();  // Flush the output stream
						os.close();  // Close output stream
						process_open.waitFor();  // Wait for command execution to complete

						// Read PIN35 GPIO
						Process process_35 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 492 + "/value");
						InputStream is_35 = process_35.getInputStream();
						StringBuilder stringBuilder_35 = new StringBuilder();
						int c_35;
						while ((c_35 = is_35.read()) != -1) {
							stringBuilder_35.append((char) c_35);
						}
						value_35 = Integer.parseInt(stringBuilder_35.toString().trim());
						// Display GPIO value
						read_gpio_tv_35.setText("VIM4 GPIO 492 the value is: " + value_35); // Display value in text view next to button

						// Read PIN37 GPIO
						Process process_37 = Runtime.getRuntime().exec("cat " + "/sys/class/gpio/gpio" + 465 + "/value");
						InputStream is_37 = process_37.getInputStream();
						StringBuilder stringBuilder_37 = new StringBuilder();
						int c_37;
						while ((c_37 = is_37.read()) != -1) {
							stringBuilder_37.append((char) c_37);
						}
						value_37 = Integer.parseInt(stringBuilder_37.toString().trim());
						// Display GPIO value
						read_gpio_tv_37.setText("VIM4 GPIO 465 the value is: " + value_37); // Display value in text view next to button
					}
				} catch (IOException | InterruptedException e) {
 					// Print exception log
 					e.printStackTrace();
					Log.e("GPIO", "Error: " + e.getMessage());
				}
			} else if (arg0.getId() == R.id.release_gpio_btn) {
				try {
					Process process_release = Runtime.getRuntime().exec("su");
					DataOutputStream os = new DataOutputStream(process_release.getOutputStream());
					// Release GPIO
					if (productName.equals("kvim1s")) {
						os.writeBytes("echo " + 503 + " > /sys/class/gpio/unexport\n");
						os.writeBytes("echo " + 508 + " > /sys/class/gpio/unexport\n");
					}
					if (productName.equals("kvim3") || productName.equals("kvim3l")) {
						os.writeBytes("echo " + 432 + " > /sys/class/gpio/unexport\n");
						os.writeBytes("echo " + 431 + " > /sys/class/gpio/unexport\n");
					}
					if (productName.equals("kvim4")) {
						os.writeBytes("echo " + 492 + " > /sys/class/gpio/unexport\n");
						os.writeBytes("echo " + 465 + " > /sys/class/gpio/unexport\n");
					}
					os.flush();
					os.close();
					process_release.waitFor();
					// Clear text view display
					read_gpio_tv_35.setText("");
					read_gpio_tv_37.setText("");
				} catch (IOException | InterruptedException e) {
 					// Print exception log
 					e.printStackTrace();
					Log.e("GPIO", "Error: " + e.getMessage());
				}
			}
		}
	};
}
