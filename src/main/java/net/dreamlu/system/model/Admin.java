package net.dreamlu.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.system.base.BaseModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author L.cm
 * @since 2018-04-01
 */
@Getter
@Setter
@TableName("t_admin")
public class Admin extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户类别[0:管理员,1:普通员工]
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 组织id
     */
    @TableField("organization_id")
    private Integer organizationId;
    /**
     * 是否锁定[0:正常,1:锁定]
     */
    private Integer locked;

}
