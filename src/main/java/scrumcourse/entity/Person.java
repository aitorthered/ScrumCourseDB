package scrumcourse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person extends BaseEntity {

	@Id @GeneratedValue private Long id;

	// @Size(min = 1, max = 40)
	// @NotNull
	@Column(length = 50, unique = true, nullable = false) private String name;

	@Column(length = 50, unique = true, nullable = false) private String email;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
