package com.chenchen.models;

public enum Sex {
    None(0),
    Man(1),
    Woman(2);

    private int sex;

    /**
     * @param sex
     */
    Sex(int sex) {
        this.sex = sex;
    }

    public static Sex valueOf(int sex) {
        switch (sex) {
            case 0:
                return None;

            case 1:
                return Man;

            case 2:
                return Woman;

            default:
                throw new IllegalArgumentException("argument out of range");
        }

    }

}
