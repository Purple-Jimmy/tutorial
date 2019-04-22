package com.tutorial.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: jimmy
 * @Date: 2019/4/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table
@Entity(name="account")
public class Account implements Serializable {
    private static final long serialVersionUID = -2897227658999048216L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=5,max=10,message = "名字长度5-10位")
    private String userName;

    //@Size(min=6,max=10)
    //@CustomValidator(values = "1,2,3")
    private String password;

    private String email;

    private String token;
}
