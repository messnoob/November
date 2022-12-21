package github.messnoob.november.controller;

import github.messnoob.november.model.pojo.Instance;
import github.messnoob.november.service.intf.ManageInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/instance")
public class ManageInstanceController {

    private ManageInstance manageInstance;

    @Autowired
    public ManageInstanceController(ManageInstance manageInstance) {
        this.manageInstance = manageInstance;
    }

    @PostMapping("/install")
    public String InstallDatabase(@RequestParam(name = "instance_name") String instance_name,
                                  @RequestParam(name = "instance_type") int instance_type,
                                  @RequestParam(name = "host_id") long host_id) {
        return manageInstance.install(instance_name, instance_type, host_id);
    }

    @PostMapping("/uninstall")
    public String UninstallDatabase(long id) {
        return manageInstance.uninstall(id);
    }

    @GetMapping("/list")
    public List<Instance> GetDatabaseRecords() {
        return manageInstance.getRecords();
    }
}
