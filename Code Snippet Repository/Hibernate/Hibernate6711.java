	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Vegetable ) ) return false;

		final Vegetable vegetable = (Vegetable) o;

		if ( !id.equals( vegetable.id ) ) return false;

		return true;
	}
