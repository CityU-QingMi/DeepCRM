	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ModifiedEntityTypeEntity) ) {
			return false;
		}

		ModifiedEntityTypeEntity that = (ModifiedEntityTypeEntity) o;

		if ( entityClassName != null ?
				!entityClassName.equals( that.entityClassName ) :
				that.entityClassName != null ) {
			return false;
		}

		return true;
	}
