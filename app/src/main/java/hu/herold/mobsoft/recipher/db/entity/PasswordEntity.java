package hu.herold.mobsoft.recipher.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by herold on 2018. 05. 12..
 */

@Entity(tableName = "passwords")
public class PasswordEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipeId")
    private String recipeId;

    @ColumnInfo(name = "passwordHash")
    private String passwordHash;

    @ColumnInfo(name = "salt")
    private String salt;


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
}
