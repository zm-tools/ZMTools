package com.zm.zmtools.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * <p><b>类说明摘要</b></p>
 * @ClassName MD5Utils
 * @Author MengMeng 
 * @Date 2018/9/1 </p>
 * @Version: 0.1
 * @Since JDK 1.80_171
 */
public class SSHUtils {

    private Channel channel;
    private Session session = null;
    private int timeout = 60000;

    public SSHUtils(final String ipAddress, final String username, final String password) throws Exception {

        JSch jsch = new JSch();
        this.session = jsch.getSession(username, ipAddress, 22);
        this.session.setPassword(password);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.setTimeout(this.timeout);
        this.session.connect();
        this.channel = this.session.openChannel("shell");
        this.channel.connect(1000);
    }

    public String runShell(String cmd, String charset) throws Exception {
        String temp = null;

        InputStream instream = null;
        OutputStream outstream = null;
        try {
            instream = this.channel.getInputStream();
            outstream = this.channel.getOutputStream();
            outstream.write(cmd.getBytes());
            outstream.flush();
            Thread.sleep(2000);
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                temp = new String(data, 0, nLen, "UTF-8");
            }
        }  finally {
            outstream.close();
            instream.close();
        }
        return temp;
    }

    public void close() {
        this.channel.disconnect();
        this.session.disconnect();
    }

    /*public static void main(final String[] args) throws Exception {
        SSHUtils sshUtil = new SSHUtils("", "root", "");
        String res = sshUtil.runShell("docker ps\n", "utf-8");
        System.out.println(res);
        sshUtil.close();
    }*/
}