package parttwo.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Task.
 *
 * @author wang_fei
 * @since 2022/5/27 11:11
 */
@Getter
@Setter
public class Task {

    private String id;

    private List<String> fid;

    private Boolean sfzd;

    private int cjsz;
}
