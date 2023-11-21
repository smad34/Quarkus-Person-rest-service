package de.indibit;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Collections;
import java.util.Map;

public class MariaDbProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Collections.singletonMap(
                "quarkus.datasource.jdbc.url",
                "jdbc:mariadb://localhost:3307/my_db_maria_person");
    }
}
