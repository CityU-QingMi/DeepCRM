	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof PrimitiveTestEntity) ) {
			return false;
		}

		PrimitiveTestEntity that = (PrimitiveTestEntity) o;

		if ( numVal1 != that.numVal1 ) {
			return false;
		}
		if ( numVal2 != that.numVal2 ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
