	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Pair) ) {
			return false;
		}

		final Pair pair = (Pair) o;

		if ( obj1 != null ? !obj1.equals( pair.obj1 ) : pair.obj1 != null ) {
			return false;
		}
		if ( obj2 != null ? !obj2.equals( pair.obj2 ) : pair.obj2 != null ) {
			return false;
		}

		return true;
	}
