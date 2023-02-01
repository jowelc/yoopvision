package jcastaneda.yoopvision.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SchemaConfigurationTest {

    @Test
    public void isShouldReturnSchemaConfiguration() {
        assertThat(SchemaConfiguration.SCHEMA_NAME).isEqualTo("yoop");
        assertThat(SchemaConfiguration.MOVIES_TABLE).isEqualTo("MOVIES");
        assertThat(SchemaConfiguration.RATINGS_TABLE).isEqualTo("RATINGS");
    }
}