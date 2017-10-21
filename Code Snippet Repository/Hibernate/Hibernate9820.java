	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof DefaultRevisionEntity) ) {
			return false;
		}

		final DefaultRevisionEntity that = (DefaultRevisionEntity) o;
		return id == that.id
				&& timestamp == that.timestamp;
	}
