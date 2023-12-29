package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Add;
import edu.pjatk.jcarsapi.repository.AddsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddServiceTest {
    private Add add1;
    private Add add2;
    @Mock
   private  AddsRepository addsRepository;
    @InjectMocks
    private  AddsService underTest;
    @BeforeEach
    void setUp(){
        add1 = new Add(1,"name1","sdsdsd",25);
        add2 = new Add(2,"name2","sdsdsd",25);
    }

    @Test
    void getAllAdds() {
        List<Add> expected = Arrays.asList(add1, add2);

        when(addsRepository.findAll()).thenReturn(expected);

        List<Add> actual = underTest.getAllAdds();

        assertEquals(expected, actual);
        verify(addsRepository, times(1)).findAll();

    }
}