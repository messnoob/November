package github.messnoob.november.model.pojo;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
public class Instance {

    @Id
    private long id;

    private String instance_name;

    private int instance_type;

    private long host_id;

    private Date create_at;

    private Date delete_at;

    private int status;

}
