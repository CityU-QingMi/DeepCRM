	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		SimplePK simplePK = (SimplePK) o;

		return id1.equals( simplePK.id1 )
				&& id2.equals( simplePK.id2 );
	}
