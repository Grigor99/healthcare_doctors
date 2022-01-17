package com.example.healthcare.migration;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ChangeLog(order = "001")
public class Logs {

    private final String DOCTORS_COLLECTION = "doctors";

    @ChangeSet(order = "001", id = "create doctors collection", author = "mongock")
    public void createDepartmentCollection(MongockTemplate db) {
        if (!db.collectionExists(DOCTORS_COLLECTION)) {
            db.createCollection(DOCTORS_COLLECTION);
        }
    }


}
