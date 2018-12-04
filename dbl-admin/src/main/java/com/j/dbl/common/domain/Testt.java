package com.j.dbl.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author chenjp
 * @create 2018-09-15 9:00
 * @desc
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="test")
public class Testt implements Serializable {

    private static final long serialVersionUID = 6581295496902103669L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "Testt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
