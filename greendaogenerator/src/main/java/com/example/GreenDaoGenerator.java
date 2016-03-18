package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {
    public static void main(String[] args)throws Exception{
        Schema schema=new Schema(1,"greendao");
        addbill(schema);
        adddaybill(schema);
        schema.setDefaultJavaPackageDao("com.smile.accountbook.dao");
        new DaoGenerator().generateAll(schema, "../AccountBook/app/src/main/java-gen");
    }

    /**
     * 添加当天总消费
     * @param schema
     */
    private static void adddaybill(Schema schema) {
        Entity daybill=schema.addEntity("DayBill");
        daybill.addIdProperty();
        daybill.addStringProperty("day");
        daybill.addDoubleProperty("money");
    }

    /**
     * 添加每次消费
     * @param schema
     */
    private static void addbill(Schema schema) {
        Entity bill=schema.addEntity("Bill");
        bill.addIdProperty();
        bill.addStringProperty("time");
        bill.addStringProperty("day");
        bill.addDoubleProperty("money");
        bill.addStringProperty("theme");
    }

}
