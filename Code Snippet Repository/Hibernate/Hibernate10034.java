	@Override
	public void mapModifiedFlagsToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		if ( getPropertyData().isUsingModifiedFlag() ) {
			data.put( getPropertyData().getModifiedFlagPropertyName(), checkModified( session, newObj, oldObj ) );
		}
	}
