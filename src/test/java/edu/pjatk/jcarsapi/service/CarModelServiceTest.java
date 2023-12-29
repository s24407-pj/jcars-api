package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.model.CarModel;
import edu.pjatk.jcarsapi.repository.CarModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarModelServiceTest {
    @InjectMocks
    CarModelService underTest;

    @Mock
    CarModelRepository carModelRepository;

    @Test
    void getAll() {
        CarModel dummyCarModel1 = new CarModel(1, "name1", new CarBrand(1, "brand1"));
        CarModel dummyCarModel2 = new CarModel(2, "name2", new CarBrand(2, "brand2"));
        List<CarModel> expected = List.of(dummyCarModel1, dummyCarModel2);

        when(carModelRepository.findAll()).thenReturn(expected);

        List<CarModel> actual = underTest.getAll();
        assertEquals(expected, actual);
        verify(carModelRepository,times(1)).findAll();
    }

    @Test
    void getById() {
        CarModel dummyCarModel = new CarModel(1, "name", new CarBrand(1, "brand"));
        Optional<CarModel> expected = Optional.of(dummyCarModel);
        int id = dummyCarModel.getId();

        when(carModelRepository.findById(id))
                .thenReturn(expected);

        Optional<CarModel> actual = underTest.getById(id);
        assertEquals(expected, actual);
        verify(carModelRepository,times(1)).findById(id);

    }

    @Test
    void deleteById() {
    int id = 1;
    underTest.deleteById(id);
    verify(carModelRepository,times(1)).deleteById(id);
    }

    @Test
    void save() {
        CarModel dummyCarModel1 = new CarModel(1, "name1", new CarBrand(1, "brand1"));

        underTest.save(dummyCarModel1);

        verify(carModelRepository, times(1)).save(dummyCarModel1);
    }
}