package service;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServiceMockTest {

    @Mock
    private StudentXMLRepository fileRepository1 = Mockito.mock(StudentXMLRepository.class);

    @Mock
    private HomeworkXMLRepository fileRepository2 = Mockito.mock(HomeworkXMLRepository.class);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllStudents() {
        Integer counter = 0;

        when(fileRepository1.findAll()).thenReturn(new ArrayList<>(
                Arrays.asList(new Student("14", "Ipsz Ilonka", 532),
                        new Student("2", "Zold Alma", 532),
                        new Student("1", "Jozsef Attila", 532))));

        for (Student s : fileRepository1.findAll()) {
            counter += 1;
        }
        assertEquals(3, counter);
    }

    @Test
    void saveStudent() {
        Integer counterBeforeInsert = 0;
        Integer counterAfterInsert = 0;

        when(fileRepository1.findAll()).thenReturn(new ArrayList<>(
                Arrays.asList(new Student("14", "Ipsz Ilonka", 532),
                        new Student("2", "Zold Alma", 532),
                        new Student("1", "Jozsef Attila", 532))));

        for (Student s : fileRepository1.findAll()) {
            counterBeforeInsert += 1;
        }

        Student student = new Student("15", "Ady Pista", 532);
        fileRepository1.save(student);

        verify(fileRepository1).save(student);

        when(fileRepository1.findAll()).thenReturn(new ArrayList<>(
                Arrays.asList(new Student("14", "Ipsz Ilonka", 532),
                        new Student("2", "Zold Alma", 532),
                        new Student("1", "Jozsef Attila", 532),
                        new Student("15", "Ady Pista", 532))));

        for (Student s : fileRepository1.findAll()) {
            counterAfterInsert += 1;
        }

        assertNotEquals(counterBeforeInsert, counterAfterInsert);
    }
}
