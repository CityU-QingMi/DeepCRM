	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof SerialNumberPk ) ) return false;

		final SerialNumberPk serialNumberPk = (SerialNumberPk) o;

		if ( !brand.equals( serialNumberPk.brand ) ) return false;
		if ( !model.equals( serialNumberPk.model ) ) return false;

		return true;
	}
