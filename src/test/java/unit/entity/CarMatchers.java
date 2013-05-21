package unit.entity;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import scrumcourse.entity.Car;

public class CarMatchers {

	public static class IsSameModel extends TypeSafeMatcher<Car> {

		private final Car expected;

		private IsSameModel(Car expected) {
			this.expected = expected;
		}

		@Override public boolean matchesSafely(Car car) {
			return car.getModel().equals(expected.getModel());
		}

		@Override public void describeTo(Description description) {
			description.appendText("is same model ");
			description.appendValue(expected.getModel());

		}

		@Factory public static <T> Matcher<Car> sameModel(Car expected) {
			return new IsSameModel(expected);
		}

	}
}
