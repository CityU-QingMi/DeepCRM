	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ExceptionListenerRevEntity) ) {
			return false;
		}

		ExceptionListenerRevEntity revEntity = (ExceptionListenerRevEntity) o;

		if ( id != revEntity.id ) {
			return false;
		}
		if ( timestamp != revEntity.timestamp ) {
			return false;
		}

		return true;
	}
