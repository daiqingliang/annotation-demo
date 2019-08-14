package com.daiql.annotationdemo.bean;

import com.daiql.annotationdemo.annotation.EnumValid;
import com.daiql.annotationdemo.annotation.NotZeroValid;
import com.daiql.annotationdemo.constants.CycleUnitEnum;

/**
 * Author daiql
 * Date 2019/8/14 14:50
 * Description 汽车保养周期
 */
public class Car {
    private String name;
    @NotZeroValid(message = "保养周期不能为0")
    private Long quotaCycle;
    @EnumValid(enumClass = CycleUnitEnum.class, enumMethod = "isValidEName", message = "保养周期单位不正确")
    private String quotaCycleType;

    public Car() {}

    public Car(String name, Long quotaCycle, String quotaCycleType) {
        this.name = name;
        this.quotaCycle = quotaCycle;
        this.quotaCycleType = quotaCycleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuotaCycle() {
        return quotaCycle;
    }

    public void setQuotaCycle(Long quotaCycle) {
        this.quotaCycle = quotaCycle;
    }

    public String getQuotaCycleType() {
        return quotaCycleType;
    }

    public void setQuotaCycleType(String quotaCycleType) {
        this.quotaCycleType = quotaCycleType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", quotaCycle=" + quotaCycle +
                ", quotaCycleType='" + quotaCycleType + '\'' +
                '}';
    }
}
