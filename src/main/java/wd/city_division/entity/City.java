package wd.city_division.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 三级行政划分数据
 * </p>
 *
 * @author WangDong
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="City对象", description="三级行政划分数据")
@TableName
public class City implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId(type = IdType.AUTO)
    private  Integer id;
    @TableField
    private String name;
    @TableField
    private String code;
    @TableField
    private Integer parentId;
    @TableField
    private String parentCode;
    @TableField
    private Integer level;
    @Transient
    @TableField(exist = false)
    private List<City> pchilds;
    @Transient
    @TableField(exist = false)
    private List<City> cchilds;
    @Transient
    @TableField(exist = false)
    private List<City> childs;
}
