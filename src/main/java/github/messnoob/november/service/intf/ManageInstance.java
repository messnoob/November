package github.messnoob.november.service.intf;

import github.messnoob.november.model.pojo.Instance;

import java.util.List;

public interface ManageInstance {

    String install(String instance_name, int instance_type, long host_id);

    String uninstall(long id);

    List<Instance> getRecords();
}
