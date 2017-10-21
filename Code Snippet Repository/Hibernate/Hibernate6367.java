	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof ChildPk ) ) return false;

		final ChildPk childPk = (ChildPk) o;

		if ( nthChild != childPk.nthChild ) return false;
		if ( !parent.equals( childPk.parent ) ) return false;

		return true;
	}
