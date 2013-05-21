package unit.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static unit.entity.CarMatchers.IsSameModel.sameModel;

import org.junit.Test;

import scrumcourse.entity.Car;

public class CarTest {

	@Test public void creatingCars() throws Exception {
		Car.Builder b = new Car.Builder();
		Car car1 = b.make("Citroen").model("C5").color("red").year(2012).build();
		Car car2 = b.model("C4").build();
		Car car3 = b.build();

		assertEquals("Citroen", car1.getMake());
		assertEquals("C5", car1.getModel());
		assertEquals("Citroen", car2.getMake());
		assertEquals("C4", car2.getModel());

		assertEquals(car2, car3);
	}

	@Test(expected = NullPointerException.class) public void creatingCarsWithoutModel() throws Exception {
		Car.Builder b = new Car.Builder();
		b.make("Citroen").color("red").year(2012).build();

	}

	@Test public void hamcrestCars() throws Exception {
		Car.Builder b = new Car.Builder();
		Car car1 = b.make("Citroen").model("C5").color("red").year(2012).build();
		Car car2 = b.model("C4").build();
		Car car3 = b.build();

		assertThat(car3, sameModel(car2));

		assertThat(car1, is(not(sameModel(car2))));
	}
}