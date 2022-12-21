package github.messnoob.november.model.pojo;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
public class Host {

    @Id
    private long id;

    private String host;

    private int port;

    private String user;

    private String password;

    private Date create_at;

    private Date delete_at;
}
