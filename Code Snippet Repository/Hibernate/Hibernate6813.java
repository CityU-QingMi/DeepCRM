	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof NodePk ) ) return false;

		final NodePk nodePk = (NodePk) o;

		if ( level != nodePk.level ) return false;
		if ( !name.equals( nodePk.name ) ) return false;

		return true;
	}
