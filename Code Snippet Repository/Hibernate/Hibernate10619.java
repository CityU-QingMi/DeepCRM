	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ProtectedConstructorEntity) ) {
			return false;
		}

		ProtectedConstructorEntity that = (ProtectedConstructorEntity) o;

		if ( wrappedStringId != null ?
				!wrappedStringId.equals( that.wrappedStringId ) :
				that.wrappedStringId != null ) {
			return false;
		}
		if ( str1 != null ? !str1.equals( that.str1 ) : that.str1 != null ) {
			return false;
		}

		return true;
	}
