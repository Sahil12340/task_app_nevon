package com.example.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.taskApp.task.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTables(final Schema schema) {
        Entity productDetail = addProduct(schema);
    }

    private static Entity addProduct(Schema schema) {
        Entity productDetaail = schema.addEntity("ProductDetail");
        productDetaail.addIdProperty().primaryKey().autoincrement();
        productDetaail.addStringProperty("productName");
        productDetaail.addIntProperty("productCost");
        productDetaail.addIntProperty("qauntity");
        productDetaail.addIntProperty("totalPrice");
        productDetaail.addBooleanProperty("isActive");
        return productDetaail;
    }
}