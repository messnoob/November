package github.messnoob.november.model.mapper;

import github.messnoob.november.model.pojo.Host;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface HostMapper {
    int addHost(String host, int port, String user, String password);
    int deleteHost(long id);
    List<Host> getHostRecords();

    Host getHostInfo(long id);
}
