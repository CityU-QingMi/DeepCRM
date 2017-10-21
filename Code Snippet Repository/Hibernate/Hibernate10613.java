	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ClassType) ) {
			return false;
		}

		ClassType classType = (ClassType) o;

		if ( type != null ? !type.equals( classType.type ) : classType.type != null ) {
			return false;
		}

		return true;
	}
