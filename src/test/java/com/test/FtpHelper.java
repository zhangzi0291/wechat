package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpHelper {

	private static Logger logger = LoggerFactory.getLogger(FtpHelper.class);

	private static FtpHelper ftpHelper;

	private FTPClient ftp;

	private FtpHelper() {
	}

	private FtpHelper(String ip, int port, String username, String password) {
		ftp = new FTPClient();
		try {
			ftp.connect(ip, port);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				try {
					logger.debug("FTP服务器拒绝连接");
					throw new Exception("FTP服务器拒绝连接");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (username != null) {
				if (!ftp.login(username, password)) {
					ftp.disconnect();
					try {
						logger.debug("登陆验证失败，请检查账号和密码是否正确");
						throw new Exception("登陆验证失败，请检查账号和密码是否正确");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			ftp.setControlEncoding("UTF-8");//这里设置编码
	        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
	        conf.setServerLanguageCode("zh");
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FtpHelper getInstance() {
		if (ftpHelper == null) {
			return new FtpHelper();
		}
		return ftpHelper;
	}

	public static FtpHelper getInstance(String ip, int port, String username, String password) {
		if (ftpHelper == null) {
			return new FtpHelper(ip.trim(), port, username.trim(), password.trim());
		}
		return ftpHelper;
	}

	public static FtpHelper getInstance(String ip, String username, String password) {
		if (ftpHelper == null) {
			return new FtpHelper(ip.trim(), 21, username.trim(), password.trim());
		}
		return ftpHelper;
	}

	public boolean downloadFile(String remotePath, String localPath) {
		File file = new File(localPath);
		try {
			remotePath = new String(remotePath.getBytes(),"ISO-8859-1");
			OutputStream os = new FileOutputStream(file);
			ftp.retrieveFile(remotePath, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean uploadFile(String path, String uploadFile, String filename) {
		boolean success = false;

			try {
				filename = new String(filename.getBytes(),"ISO-8859-1");
				InputStream input = new FileInputStream(uploadFile);
				ftp.changeWorkingDirectory(path);
				ftp.storeFile(filename, input);
				input.close();
				success = true;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return success;
	}

	public List<FTPFile> getFtpFileList(String remotePath) {
		List<FTPFile> ftpfiles = null;
		try {
			ftpfiles = Arrays.asList(ftp.listFiles(remotePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpfiles;
	}
	
	public List<FTPFile> getNowFileList(){
		List<FTPFile> ftpfiles = null;
		try {
			ftpfiles = Arrays.asList(ftp.listFiles());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpfiles;
	}
	
	public void closeClient() {
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean setPath(String remoteFoldPath) {
		try {
			return ftp.changeWorkingDirectory(remoteFoldPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		FtpHelper ftp = FtpHelper.getInstance("127.0.0.1", 21, "zero", "zero");
//		ftp.downloadFile("/2.jpg", "D:/2.jpg");
//		ftp.uploadFile("/", "F:/化作樱花树.txt", "化作樱花树.txt");
		System.out.println(ftp.getNowFileList());
		ftp.setPath("../");
		for(FTPFile f:ftp.getFtpFileList("/")){
			System.out.println(f.getName());
		}
		ftp.closeClient();
	}
}
