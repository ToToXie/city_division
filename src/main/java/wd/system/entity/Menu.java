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
 * 菜单表
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单
     */
    public static final String TYPE_MENU = "0";
    /**
     * 按钮
     */
    public static final String TYPE_BUTTON = "1";

    public static final Long TOP_NODE = 0L;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "上级菜单ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("MENU_NAME")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    @TableField("URL")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("PERMS")
    private String perms;

    @ApiModelProperty(value = "图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("ORDER_NUM")
    private Long orderNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;


}
