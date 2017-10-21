	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Soldier2 ) ) return false;

		final Soldier2 soldier = (Soldier2) o;

		if ( !name.equals( soldier.name ) ) return false;

		return true;
	}
