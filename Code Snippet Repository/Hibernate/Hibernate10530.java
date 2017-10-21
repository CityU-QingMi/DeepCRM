	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ObjectUserTypeEntity) ) {
			return false;
		}

		ObjectUserTypeEntity that = (ObjectUserTypeEntity) o;

		if ( id != that.id ) {
			return false;
		}
		if ( buildInType != null ? !buildInType.equals( that.buildInType ) : that.buildInType != null ) {
			return false;
		}
		if ( userType != null ? !userType.equals( that.userType ) : that.userType != null ) {
			return false;
		}

		return true;
	}
