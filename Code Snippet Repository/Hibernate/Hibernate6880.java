	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof ComputerPk ) ) return false;

		final ComputerPk computerPk = (ComputerPk) o;

		if ( !brand.equals( computerPk.brand ) ) return false;
		if ( !model.equals( computerPk.model ) ) return false;

		return true;
	}
