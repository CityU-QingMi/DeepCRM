	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Pet pet = (Pet) o;

		if (name != null ? !name.equals(pet.name) : pet.name != null) return false;

		return true;
	}
