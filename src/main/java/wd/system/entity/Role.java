package wd.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
import java.util.Date;

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
    @ExcelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    @ExcelProperty("角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("REMARK")
    @ExcelProperty("角色描述")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @ExcelProperty(value = "创建时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    @ExcelProperty("修改时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date modifyTime;
    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;

}
