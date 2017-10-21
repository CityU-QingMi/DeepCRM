	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final Brand brand = (Brand) o;

		if ( !name.equals( brand.name ) ) return false;

		return true;
	}
