	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		PersonName name = ( PersonName ) o;

		if ( first != null ? !first.equals( name.first ) : name.first != null ) {
			return false;
		}
		if ( middle != null ? !middle.equals( name.middle ) : name.middle != null ) {
			return false;
		}
		if ( last != null ? !last.equals( name.last ) : name.last != null ) {
			return false;
		}

		return true;
	}
