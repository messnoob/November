package github.messnoob.november.service.impl;

import github.messnoob.november.model.mapper.HostMapper;
import github.messnoob.november.model.mapper.InstanceMapper;
import github.messnoob.november.model.pojo.Host;
import github.messnoob.november.model.pojo.Instance;
import github.messnoob.november.scripts.OperationEnum;
import github.messnoob.november.utils.ExecuteScripts;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageInstance implements github.messnoob.november.service.intf.ManageInstance {

    @Autowired
    private SqlSession sqlSession = null;

    @Autowired
    private HostMapper hostMapper = null;
    @Autowired
    private InstanceMapper instanceMapper = null;

    private ExecuteScripts executeScripts = new ExecuteScripts();
    @Override
    public String install(String instance_name, int instance_type, long host_id) {
        try {
            //查询对应主机信息
            Host installHost = hostMapper.getHostInfo(host_id);
            //到对应主机执行脚本
            instanceMapper.installInstance(instance_name, instance_type, host_id);
            executeScripts.execute(installHost, OperationEnum.INSTALL_DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
            return "安装数据库实例失败";
        }
        return "安装数据库实例成功";
    }

    @Override
    public String uninstall(long id) {
        try {
            //查询对应主机信息

            //到对应主机执行脚本
            instanceMapper.uninstallInstance(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "卸载数据库实例失败";
        }
        return "卸载数据库实例成功";
    }

    @Override
    public List<Instance> getRecords() {
        return instanceMapper.getInstanceRecords();
    }

}
