	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Clothes ) ) return false;

		final Clothes clothes = (Clothes) o;

		if ( !flavor.equals( clothes.flavor ) ) return false;
		if ( !type.equals( clothes.type ) ) return false;

		return true;
	}
