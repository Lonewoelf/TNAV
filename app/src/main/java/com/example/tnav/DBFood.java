package com.example.tnav;

public class DBFood {
    public static final String TABLE_NAME = "foodtbl";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITEL = "Titel";
    public static final String COLUMN_DESC = "Desc";

    private String Title, Desc;
    private int id;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITEL + " TEXT,"
                    + COLUMN_DESC + " TEXT"
                    + ")";

    public DBFood() {

    }

    public DBFood(int id, String Title, String Desc) {
        this.id = id;
        this.Title = Title;
        this.Desc = Desc;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

}
