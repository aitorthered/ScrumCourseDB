package scrumcourse.entity;

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

public class Car {

	private String make;
	private String model;
	private String color;
	private int year;

	private Car() {
	}

	@Override public String toString() {
		return toStringHelper(this).omitNullValues().add("make", make).add("model", model).toString();
	}

	@Override public int hashCode() {
		return Objects.hash(make, model, color, year);
	}

	@Override public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(make, other.make) && Objects.equals(model, other.model)
				&& Objects.equals(color, other.color) && Objects.equals(year, other.year);
	}

	public int hashCodeEclipse() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (color == null ? 0 : color.hashCode());
		result = prime * result + (make == null ? 0 : make.hashCode());
		result = prime * result + (model == null ? 0 : model.hashCode());
		result = prime * result + year;
		return result;
	}

	public boolean equalsEclipse(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public int getYear() {
		return year;
	}

	public static class Builder {
		private String make;
		private String model;
		private String color;
		private int year;

		public Builder make(String make) {
			this.make = make;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public Builder year(int year) {
			this.year = year;
			return this;
		}

		public Car build() {
			Car car = new Car();
			checkNotNull(car.make = make);
			checkNotNull(car.model = model);
			checkNotNull(car.color = color);
			checkArgument(year > 1970 && year < 2020);
			car.year = year;
			return car;
		}

	}

}
