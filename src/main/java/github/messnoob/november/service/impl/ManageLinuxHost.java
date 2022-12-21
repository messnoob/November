package github.messnoob.november.service.impl;

import github.messnoob.november.model.mapper.HostMapper;
import github.messnoob.november.model.pojo.Host;
import github.messnoob.november.service.intf.ManageHost;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ManageLinuxHost implements ManageHost {

    @Autowired
    private SqlSession sqlSession = null;
    @Autowired
    private HostMapper hostMapper = null;

    @Override
    public String add(String host, int port, String user, String password) {
        try {
            if (hostMapper.addHost(host, port, user, password) == 1) {
                return "添加主机成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "添加主机失败";
        }
        return "添加主机失败";
    }

    @Override
    public String remove(long id) {
        //todo: 添加删除失败判断，如id所对应的主机记录不存在
        try {
            if (hostMapper.deleteHost(id) == 1) {
                return "删除主机成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "删除主机失败";
        }
        return "删除主机失败";
    }

    @Override
    public List<Host> getRecords() {
        return hostMapper.getHostRecords();
    }
}
