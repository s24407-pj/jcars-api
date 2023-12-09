package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.model.CarModel;
import edu.pjatk.jcarsapi.repository.CarRepository;
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

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService underTest;

    @Test
    void getAll() {
        Car car1 = new Car(1,new CarModel(1,"carModel",new CarBrand(1,"carBrand")),2013,"GST12345",25,true,20000,"http//www.image.com","Lorem ipsum");
        Car car2 = new Car(2,new CarModel(2,"carModel2",new CarBrand(2,"carBrand2")),2003,"GDA12345",15,false,21000,"http//www.imssage.com","Lorsssem ipsum");

        List<Car> expectedCars = List.of(car1, car2);

        when(carRepository.findAll()).thenReturn(expectedCars);

        List<Car> actualCars = underTest.getAll();

        assertEquals(expectedCars, actualCars);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        Car expectedCar = new Car(1,new CarModel(1,"carModel",new CarBrand(1,"carBrand")),2013,"GST12345",25,true,20000,"http//www.image.com","Lorem ipsum");

        Integer id = expectedCar.getId();

        when(carRepository.findById(id)).thenReturn(Optional.of(expectedCar));

        Optional<Car> actualCar = underTest.getById(id);

        assertEquals(Optional.of(expectedCar), actualCar);
        verify(carRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        Car car = new Car(1,new CarModel(1,"carModel",new CarBrand(1,"carBrand")),2013,"GST12345",25,true,20000,"http//www.image.com","Lorem ipsum");

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