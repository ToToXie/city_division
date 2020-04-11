package wd.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;
    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;

}
