package com.example.domains.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

    private static Validator validator;
    private Actor actor;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        actor = new Actor(1, "PENELOPE", "GUINESS");
    }

    @Test
    void testValidActor() {
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidFirstName_MaxLength() {
        String longFirstName = "A".repeat(45);
        actor.setFirstName(longFirstName);
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Violaciones encontradas:\n");
            for (ConstraintViolation<Actor> violation : violations) {
                errorMessage.append("  - ").append(violation.getMessage()).append("\n");
            }
            fail(errorMessage.toString()); 
        }

        assertTrue(violations.isEmpty());
    }
    
    @Test
    void testValidFirstName_MinLength() {
        actor.setFirstName("AB");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidFirstName_ValidPattern_Spaces() {
        actor.setFirstName("PENELOPE GUINESS");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

   
    @Test
    void testValidLastName_MaxLength() {
        actor.setLastName("A".repeat(45));
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidLastName_MinLength() {
        actor.setLastName("AB");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidLastName_ValidPattern_Spaces() {
        actor.setLastName("GUINESS TEST");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidFirstName_Empty() {
        actor.setFirstName("");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());
        
        boolean hasExpectedMessage = false;
        for (ConstraintViolation<Actor> violation : violations) {
            if (violation.getMessage().equals("El nombre no puede estar vacío")) {
                hasExpectedMessage = true;
                break;
            }
        }
        
        assertTrue(hasExpectedMessage, "Expected violation message not found");
    }
    
    @Test
    void testInvalidFirstName_TooShort() {
        actor.setFirstName("A");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe tener entre 2 y 45 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidFirstName_TooLong() {
        actor.setFirstName("A".repeat(46));
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe tener entre 2 y 45 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidFirstName_InvalidPattern_Lowercase() {
        actor.setFirstName("penelope");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidFirstName_InvalidPattern_Numbers() {
        actor.setFirstName("PENELOPE123");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidFirstName_InvalidPattern_SpecialChars() {
        actor.setFirstName("PENELOPE@");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidFirstName_InvalidPattern_MixedCase() {
        actor.setFirstName("PeNeLoPe");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

   
    @Test
    void testInvalidLastName_Empty() {
        actor.setLastName("");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size()); 
        
        
        boolean hasExpectedMessage = false;
        for (ConstraintViolation<Actor> violation : violations) {
            if (violation.getMessage().equals("El apellido no puede estar vacío")) {
                hasExpectedMessage = true;
                break;
            }
        }
        
        assertTrue(hasExpectedMessage, "Expected violation message not found");
    }

    @Test
    void testInvalidLastName_TooShort() {
        actor.setLastName("A");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe tener entre 2 y 45 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastName_TooLong() {
        actor.setLastName("A".repeat(46));
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe tener entre 2 y 45 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastName_InvalidPattern_Lowercase() {
        actor.setLastName("guiness");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastName_InvalidPattern_Numbers() {
        actor.setLastName("GUINESS123");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastName_InvalidPattern_SpecialChars() {
        actor.setLastName("GUINESS@");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastName_InvalidPattern_MixedCase() {
        actor.setLastName("GuInNeSs");
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El apellido debe estar en mayúsculas y no contener números", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidLastUpdate_NotNull() {
        actor.setLastUpdate(Timestamp.from(Instant.now()));
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("La fecha de actualización se asigna automáticamente", violations.iterator().next().getMessage());
    }

    @Test
    void testHasValidName_Valid() {
        assertTrue(actor.hasValidName());
    }

    @Test
    void testHasValidName_InvalidFirstName() {
        actor.setFirstName("penelope");
        assertFalse(actor.hasValidName());
    }

    @Test
    void testHasValidName_InvalidLastName() {
        actor.setLastName("guiness");
        assertFalse(actor.hasValidName());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(actor, actor);
    }

    @Test
    void testEquals_SameId() {
        Actor actor2 = new Actor(1, "ED", "CHASE");
        assertEquals(actor, actor2);
    }

    @Test
    void testEquals_DifferentId() {
        Actor actor2 = new Actor(2, "ED", "CHASE");
        assertNotEquals(actor, actor2);
    }

    @Test
    void testEquals_Null() {
        assertNotEquals(actor, null);
    }

    @Test
    void testEquals_DifferentClass() {
        assertNotEquals(actor, "Not an Actor");
    }

    @Test
    void testHashCode_SameId() {
        Actor actor2 = new Actor(1, "ED", "CHASE");
        assertEquals(actor.hashCode(), actor2.hashCode());
    }
    
    @Test
    void testHashCode_DifferentId() {
        Actor actor2 = new Actor(2, "ED", "CHASE");
        assertNotEquals(actor.hashCode(), actor2.hashCode());
    }

 
    @Test
    void testToString() {
    }
};