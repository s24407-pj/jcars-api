package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Attribute;
import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.model.CarModel;
import edu.pjatk.jcarsapi.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
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
class CarServiceTest {
    private Car car1;
    private Car car2;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService underTest;

    @BeforeEach
    void setUp() {
        car1 = new Car(
                1,
                new CarModel(1, "name", new CarBrand(1, "name")),
                2022,
                "ABC123",
                500,
                50,
                List.of(new Attribute(1, "name"), new Attribute(2, "name2")),
                true,
                10000,
                "https://dummy.com/car",
                "This is a dummy car"
        );
        car2 = new Car(
                2,
                new CarModel(2, "name", new CarBrand(2, "name")),
                2012,
                "ABCdd123",
                500,
                50,
                List.of(new Attribute(2, "name"), new Attribute(3, "name2")),
                true,
                10200,
                "https://duddmmy.com/car",
                "This ids a dummy car"
        );

    }

    @Test
    void getAll() {
        List<Car> expectedCars = List.of(car1, car2);

        when(carRepository.findAll()).thenReturn(expectedCars);

        List<Car> actualCars = underTest.getAll();

        assertEquals(expectedCars, actualCars);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        Car expectedCar = car1;
        Integer id = expectedCar.getId();

        when(carRepository.findById(id)).thenReturn(Optional.of(expectedCar));

        Optional<Car> actualCar = underTest.getById(id);

        assertEquals(Optional.of(expectedCar), actualCar);
        verify(carRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        Car car = car1;
        underTest.save(car);

        verify(carRepository, times(1)).save(car);
    }

    @Test
    void deleteById() {
        Integer id = 1;

        underTest.deleteById(id);

        verify(carRepository, times(1)).deleteById(id);
    }
}