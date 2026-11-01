package com.amigoscode.bughunt.medium.bug57;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypeSafeMapTest {

    @Test
    void getWithCorrectTypeReturnsValue() {
        TypeSafeMap map = new TypeSafeMap();
        map.put("name", "Alice");

        String name = map.get("name", String.class);

        assertThat(name).isEqualTo("Alice");
    }

    @Test
    void getWithWrongTypeShouldReturnCorrectType() {
        TypeSafeMap map = new TypeSafeMap();
        map.put("age", "42");

        assertThrows(ClassCastException.class, () -> map.get("age", Integer.class));
    }

    @Test
    void getOptionalWithWrongTypeShouldReturnCorrectType() {
        TypeSafeMap map = new TypeSafeMap();
        map.put("count", "42");

        assertThrows(ClassCastException.class, () -> map.getOptional("count", Integer.class));
    }

    @Test
    void getMissingKeyReturnsNull() {
        TypeSafeMap map = new TypeSafeMap();

        String result = map.get("missing", String.class);

        assertThat(result).isNull();
    }
}
