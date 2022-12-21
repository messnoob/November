package github.messnoob.november.model.mapper;


import github.messnoob.november.model.pojo.Instance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InstanceMapper {
    int installInstance(String instance_name, int instance_type, long host_id);
    int uninstallInstance(long id);
    List<Instance> getInstanceRecords();
}
