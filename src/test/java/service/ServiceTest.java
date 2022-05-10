package service;

import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private StudentXMLRepository fileRepository1;

    private HomeworkXMLRepository fileRepository2;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "src/test/resources/students.xml");
        fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "src/test/resources/homework.xml");
    }

    @Test
    void findAllStudents() {
        Integer counter = 0;

        for (Student s : fileRepository1.findAll()) {
            counter += 1;
        }
        assertEquals(3, counter);
    }

    @Test
    void findAllHomework() {
        Integer counter = 0;

        for (Homework h : fileRepository2.findAll()) {
            counter += 1;
        }
        assertEquals(3, counter);
    }

    @Test
    void saveStudent() {
        Integer counterBeforeInsert = 0;
        Integer counterAfterInsert = 0;

        for (Student s : fileRepository1.findAll()) {
            counterBeforeInsert += 1;
        }

        Student student = new Student("14", "Ipsz Ilonka", 532);
        fileRepository1.save(student);

        for (Student s : fileRepository1.findAll()) {
            counterAfterInsert += 1;
        }

        assertNotEquals(counterBeforeInsert, counterAfterInsert);
    }

    @Test
    void deleteStudent() {
        Integer counterBeforeDelete = 0;
        Integer counterAfterDelete = 0;

        for (Student s : fileRepository1.findAll()) {
            counterBeforeDelete += 1;
        }

        fileRepository1.delete("14");

        for (Student s : fileRepository1.findAll()) {
            counterAfterDelete += 1;
        }

        assertTrue(counterAfterDelete.equals(counterBeforeDelete - 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2", "156", "-69", "3658", "-96584"})
    void deleteHomeworkWithNonExistingId(String id) {
        Integer counterBeforeDelete = 0;
        Integer counterAfterDelete = 0;

        for (Homework h : fileRepository2.findAll()) {
            counterBeforeDelete += 1;
        }

        fileRepository2.delete(id);

        for (Homework h : fileRepository2.findAll()) {
            counterAfterDelete += 1;
        }

        assertEquals(counterBeforeDelete, counterAfterDelete);
    }
}