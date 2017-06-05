package com.unbank.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SendFileBySftp {
	static Log logger = LogFactory.getLog(SendFileBySftp.class);

	/**
	 * 使用sftp上传文件到服务器
	 * 
	 * @param ip
	 *            sftp ip
	 * @param port
	 *            sftp 端口号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param path
	 *            要上传文件在本地的路径
	 * @param filename
	 *            上传文件名称
	 * @param instream
	 * @return
	 */
	public boolean uploadFile(String ip, int port, String username,
			String password, String path, String filename, InputStream instream) {
		Session session = null;
		Channel channel = null;
		try {
			JSch jsch = new JSch();
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(username, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(username, ip, port);
			}

			// 如果服务器连接不上，则抛出异常
			if (session == null) {
				logger.info("连接Sftp服务器失败");
				return false;
				// throw new Exception("session is null");
			}
			// 设置登陆主机的密码
			session.setPassword(password);// 设置密码
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置登陆超时时间
			session.connect(1000 * 60 * 30);
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(1000 * 60 * 30);
			ChannelSftp sftp = (ChannelSftp) channel;
			try {
				Vector content = sftp.ls(path);
				if (content == null) {
					sftp.mkdir(path);
				}
			} catch (SftpException e) {
				logger.error("sftp 服务器上没有该文件夹" + path, e);
				sftp.mkdir(path);
			}
			sftp.cd(path);
			OutputStream outstream = sftp.put(filename,
					new MyProgressMonitor(), ChannelSftp.OVERWRITE);
			byte b[] = new byte[1024 * 1024 * 5];
			int n;
			while ((n = instream.read(b)) != -1) {
				outstream.write(b, 0, n);
			}
			outstream.flush();
			outstream.close();
			instream.close();
		} catch (Exception e) {
			logger.error("传输文件到Sftp服务器失败", e);
			return false;
		} finally {
			if (session != null) {
				session.disconnect();
			}
			if (channel != null) {
				channel.disconnect();
			}
		}
		return true;

	}

	public static void main(String[] args) {
		File file = new File("country_datafield_month.json");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new SendFileBySftp().uploadFile("10.0.2.126", 22, "sftpuser",
				"Windows2008", "", file.getName(), input);
	}
}
