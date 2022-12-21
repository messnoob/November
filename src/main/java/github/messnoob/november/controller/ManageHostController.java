package github.messnoob.november.controller;

import github.messnoob.november.model.pojo.Host;
import github.messnoob.november.service.intf.ManageHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/host")
public class ManageHostController {

    @Autowired
    private ManageHost manageHost;

    @PostMapping("/add")
    public String AddHost(@RequestParam(name = "host") String host,
                           @RequestParam(name = "port") int port,
                           @RequestParam(name = "user") String user,
                           @RequestParam(name = "password") String password)
    {
        return manageHost.add(host, port, user, password);
    }

    @PostMapping("/remove")
    public String DeleteHost(@RequestParam(name = "id") long id) {
        return manageHost.remove(id);
    }

    @GetMapping("/list")
    public List<Host> GetHostRecords() {
        return manageHost.getRecords();
    }
}
