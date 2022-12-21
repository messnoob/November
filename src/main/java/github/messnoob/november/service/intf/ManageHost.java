package github.messnoob.november.service.intf;

import github.messnoob.november.model.pojo.Host;

import java.util.List;

public interface ManageHost {

    String add(String host, int port, String user, String password);

    String remove(long id);

    List<Host> getRecords();
}
