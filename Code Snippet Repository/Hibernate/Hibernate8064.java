	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Constructor ) ) return false;

		Constructor that = (Constructor) o;

		if ( anotherBoolean != that.anotherBoolean ) return false;
		if ( id != that.id ) return false;
		if ( someBoolean != that.someBoolean ) return false;
		if ( someNumber != null ? !someNumber.equals( that.someNumber ) : that.someNumber != null ) return false;
		if ( someString != null ? !someString.equals( that.someString ) : that.someString != null ) return false;

		return true;
	}
