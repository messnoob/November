package github.messnoob.november.utils;


import com.jcraft.jsch.*;
import github.messnoob.november.model.pojo.Host;
import github.messnoob.november.scripts.OperationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ExecuteScripts {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteScripts.class);

    public static String SCRIPT_HOME = "/home/nvb/scripts/";
    public static String INSTALL_DATABASE = "install_database.sh";

    public int execute(Host host, OperationEnum operation) {
        JSch jSch = new JSch();
        Session session = null;
        Channel channel = null;

        StringBuffer result = new StringBuffer();
        int exitStatus = 0;
        String command = "/bin/sh " + SCRIPT_HOME;

        switch (operation) {
            case INSTALL_DATABASE:
                command += INSTALL_DATABASE;
                break;
                //todo: 后续根据其它情况添加调用其它脚本的command
        }

        try {
            session = jSch.getSession(host.getUser(), host.getHost(), host.getPort());
            session.setPassword(host.getPassword());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(10000);
            session.connect();

            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();

            InputStream in = channel.getInputStream();

            byte[] tmp = new byte[1024];
            while(true) {
                while(in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if ( i < 0 ) {
                        break;
                    }
                    result.append(new String(tmp, 0, i));
                }

                if(channel.isClosed()) {
                    if(in.available() > 0) {
                        continue;
                    }
                    exitStatus = channel.getExitStatus();
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {}
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        logger.info("获取执行命令的结果：" + result);
        logger.info("退出码为：" + exitStatus);

        return exitStatus;
    }


}


