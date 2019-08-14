package com.daiql.annotationdemo.constants;

/**
 * 周期类型枚举类
 */
public enum CycleUnitEnum {
    DAY("天", "d", 24*60*60L),
    WEEK("周", "w", 7*24*60*60L),
    MONTH("月", "m", 30*24*60*60L),
    YEAR("年", "y", 365*24*60*60L),
    FOREVER("不限", "f", 100*365*24*60*60L),
    END("结束", "end", 0);

    /**
     * 中文名称
     */
    private String name;
    /**
     * 枚举名称
     */
    private String eName;
    /**
     * 转换为秒数
     */
    private long seconds;

    CycleUnitEnum(String name, String eName, long seconds) {
        this.name = name;
        this.eName = eName;
        this.seconds = seconds;
    }


    public static boolean isValidName(String name) {
        for (CycleUnitEnum cylceUnit : CycleUnitEnum.values()) {
            if (cylceUnit.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEName(String eNamename) {
        for (CycleUnitEnum cylceUnit : CycleUnitEnum.values()) {
            if (cylceUnit.geteName().equals(eNamename)) {
                return true;
            }
        }
        return false;
    }

    public static long getSecondsByEName(String eName) {
        for (CycleUnitEnum cylceUnit : CycleUnitEnum.values()) {
            if (cylceUnit.geteName().equals(eName)) {
                return cylceUnit.seconds;
            }
        }
        return 0L;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
