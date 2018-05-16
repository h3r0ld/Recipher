package hu.herold.mobsoft.recipher.db;

import hu.herold.mobsoft.recipher.db.dao.PasswordDao;
import hu.herold.mobsoft.recipher.db.entity.PasswordEntity;

/**
 * Created by herold on 2018. 05. 16..
 */

public class MockPasswordDao implements PasswordDao {
    @Override
    public PasswordEntity getPassword(String recipeId) {
        return null;
    }

    @Override
    public void savePassword(PasswordEntity passwordEntity) {

    }

    @Override
    public void deletePassword(PasswordEntity passwordEntity) {

    }
}
