	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Person person = (Person) o;

		if (id != null ? !id.equals(person.id) : person.id != null) return false;

		return true;
	}
