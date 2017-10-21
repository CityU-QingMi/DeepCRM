	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final Toy toy = (Toy) o;

		if ( !brand.equals( toy.brand ) ) return false;
		if ( !name.equals( toy.name ) ) return false;
		if ( !serial.equals( toy.serial ) ) return false;

		return true;
	}
