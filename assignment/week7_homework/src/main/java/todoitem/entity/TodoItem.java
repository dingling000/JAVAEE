package todoitem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel(description="待办事项实体")
@Entity
public class TodoItem {

        @ApiModelProperty("待办事项编号")
        @Id
        @GeneratedValue
        long id;
        @ApiModelProperty("待办事项名称")
        String name;
        @ApiModelProperty("是否完成")
        boolean complete;
}
