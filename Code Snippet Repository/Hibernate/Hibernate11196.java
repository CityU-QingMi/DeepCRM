	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ListenerRevEntity) ) {
			return false;
		}

		ListenerRevEntity revEntity = (ListenerRevEntity) o;

		if ( id != revEntity.id ) {
			return false;
		}
		if ( timestamp != revEntity.timestamp ) {
			return false;
		}

		return true;
	}
