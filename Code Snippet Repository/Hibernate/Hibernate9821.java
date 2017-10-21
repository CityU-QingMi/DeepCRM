	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof DefaultTrackingModifiedEntitiesRevisionEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		final DefaultTrackingModifiedEntitiesRevisionEntity that = (DefaultTrackingModifiedEntitiesRevisionEntity) o;

		if ( modifiedEntityNames != null ? !modifiedEntityNames.equals( that.modifiedEntityNames )
				: that.modifiedEntityNames != null ) {
			return false;
		}

		return true;
	}
