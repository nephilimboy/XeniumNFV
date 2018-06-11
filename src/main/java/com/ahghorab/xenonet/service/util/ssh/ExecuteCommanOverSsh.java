package com.ahghorab.xenonet.service.util.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/*
 * 
 * This class is responsible for execute SSH command on remote server
 * "sendCommand" method getting list array of command if the array has size = 1
 * the corresponding mapper, split the result by "\\r?\\n" (new line) and map them
 * to the mapper strings. if the command array list has size > 1, it map the multiple 
 * output to mapper. NOTICE that JSCH by default use 10 channel per session u can 
 * uncrease it by adding new configuration "config.put("MaxSessions", "20");" like this.
 * but as my experience it doesn't work properly so i combine all command to the single 
 * command.
 * 
 */
public class ExecuteCommanOverSsh {

	public String command;
	public String hostIp;
	public String userName;
	public String pass;

	private JSch jschSSH = null;
	private Session session = null;

	private List<Channel> channelArray = new ArrayList<Channel>();;

	private List<InputStream> inputStreamArray = new ArrayList<InputStream>();;

	private List<OutputStream> outputStreamArray = new ArrayList<OutputStream>();

	private List<String> mapper = new ArrayList<String>();

	public ExecuteCommanOverSsh() {

	}

	public boolean openConnection(String strHost, int iport, String strUserName, String strPassword, int iTimeout,
			List<String> mapper) {

		this.mapper = mapper;

		boolean blResult = false;

		this.jschSSH = new JSch();

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		// config.put("MaxSessions", "20");
		this.jschSSH.setConfig(config);
		try {
			this.session = this.jschSSH.getSession(strUserName, strHost, iport);
			this.session.setPassword(strPassword);
			this.session.connect(iTimeout);
			// this.channel.setInputStream(null);
			blResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blResult;
	}

	public boolean connect(int channelNumber) {
		try {
			this.channelArray.get(channelNumber).connect();
			this.outputStreamArray.get(channelNumber).write(("mininet" + "\n").getBytes());
			this.outputStreamArray.get(channelNumber).flush();
			return true;
		} catch (Exception e) {
			System.out.println("Channel connection" + channelNumber);
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendCommand(List<String> commandStringArray) {
		boolean blResulat = false;
		for (int i = 0; i < commandStringArray.size(); i++) {
			try {
				try {
					Channel channel = this.session.openChannel("exec");
					this.channelArray.add(channel);
				} catch (JSchException e) {
					System.out.println("Cant Open Channel On Iteration: " + i);
					e.printStackTrace();
				}
				OutputStream outputStream = this.channelArray.get(i).getOutputStream();
				this.outputStreamArray.add(outputStream);

			} catch (IOException e1) {
				System.out.println("Cant Init Output Stream on Iteration: " + i);
				e1.printStackTrace();
			}
			try {
				if (this.outputStreamArray.get(i) != null) {
					((ChannelExec) this.channelArray.get(i)).setCommand("sudo -S -p '' " + commandStringArray.get(i));

					InputStream inputStream = this.channelArray.get(i).getInputStream();
					this.inputStreamArray.add(inputStream);

					if (connect(i) == true) {
						blResulat = true;
					} else {
						blResulat = false;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Channel send command" + i);
				e.printStackTrace();
			}
		}
		;
		return blResulat;
	}

	public Map<String, String> recvData() {

		Map<String, String> result = new HashMap<String, String>();

		for (int j = 0; j < this.channelArray.size(); j++) {
			try {
				if (this.inputStreamArray.get(j) != null) {
					byte[] tmp = new byte[1024];
					while (true) {
						while (this.inputStreamArray.get(j).available() > 0) {

							int i = this.inputStreamArray.get(j).read(tmp, 0, 1024);
							if (i < 0)
								break;
							// MAP OUTPUT TO THE MAPPER
							if (this.mapper != null) {
								if (this.channelArray.size() == 1) {
									for (int k = 0; k < this.mapper.size(); k++) {
										result.put(this.mapper.get(k), new String(tmp, 0, i).split("\\r?\\n")[k]);
									}
								} else {
									result.put(this.mapper.get(j), new String(tmp, 0, i));
								}
							}
						}

						if (this.channelArray.get(j).isClosed()) {
							if (this.inputStreamArray.get(j).available() > 0)
								continue;
							System.out.println("exit-status: " + this.channelArray.get(j).getExitStatus());
							break;
						}
						try {
							Thread.sleep(1000);
						} catch (Exception ee) {
						}
					}

				}
			} catch (Exception e) {
				System.out.println("Channel receive data" + j);
				e.printStackTrace();
			}
		}
		if (this.mapper != null) {
			return result;
		} else {
			return null;
		}
	}

	public void close() {
		for (int j = 0; j < this.channelArray.size(); j++) {
			if (this.session != null) {
				this.session.disconnect();
			}
			if (this.channelArray.get(j) != null) {
				this.channelArray.get(j).disconnect();
			}
			if (this.inputStreamArray.get(j) != null) {
				try {
					this.inputStreamArray.get(j).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.outputStreamArray.get(j) != null) {
				try {
					this.outputStreamArray.get(j).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.jschSSH = null;
	}

}