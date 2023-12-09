package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.repository.CarBrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarBrandServiceTest {

    @InjectMocks
    CarBrandService underTest;

    @Mock
    CarBrandRepository carBrandRepository;

    @Test
    void getAll() {
        CarBrand carBrand1 = new CarBrand(1,"name1");
        CarBrand carBrand2 = new CarBrand(2,"name2");
        List<CarBrand> expected = Arrays.asList(carBrand1, carBrand2);

        when(carBrandRepository.findAll()).thenReturn(expected);

        List<CarBrand> actual = underTest.getAll();

        assertEquals(expected, actual);
        verify(carBrandRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        CarBrand carBrand = new CarBrand();
        Integer id = 1;

        when(carBrandRepository.findById(id)).thenReturn(Optional.of(carBrand));

        Optional<CarBrand> actual = underTest.getById(id);

        assertEquals(Optional.of(carBrand), actual);
        verify(carBrandRepository, times(1)).findById(id);
    }

    @Test
    void deleteById() {
        Integer id = 1;

        underTest.deleteById(id);

        verify(carBrandRepository, times(1)).deleteById(id);
    }

    @Test
    void save() {
        CarBrand carBrand = new CarBrand();

        underTest.save(carBrand);

        verify(carBrandRepository, times(1)).save(any(CarBrand.class));
    }
}