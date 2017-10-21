	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Soldier ) ) return false;

		final Soldier soldier = (Soldier) o;

		if ( name != null ? !name.equals( soldier.name ) : soldier.name != null ) return false;

		return true;
	}
