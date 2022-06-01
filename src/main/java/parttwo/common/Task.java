package parttwo.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 任务.
 *
 * @author wang_fei
 * @since 2022/5/27 11:11
 */
@Getter
@Setter
public class Task {

    private String id;

    private List<String> fid;

    private int cjsd;

    private List<? extends Task> children;
}
