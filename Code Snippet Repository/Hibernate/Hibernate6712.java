	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof VegetablePk ) ) return false;

		final VegetablePk vegetablePk = (VegetablePk) o;

		if ( !farmer.equals( vegetablePk.farmer ) ) return false;
		if ( !harvestDate.equals( vegetablePk.harvestDate ) ) return false;

		return true;
	}
