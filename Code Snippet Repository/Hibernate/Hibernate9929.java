	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof SequenceIdRevisionEntity) ) {
			return false;
		}

		final SequenceIdRevisionEntity that = (SequenceIdRevisionEntity) o;
		return id == that.id && timestamp == that.timestamp;
	}
