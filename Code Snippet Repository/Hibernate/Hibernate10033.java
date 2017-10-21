	@Override
	public boolean mapToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		final HashMap<String, Object> newData = new HashMap<>();

		// If this property is originally non-insertable, but made insertable because it is in a many-to-one "fake"
		// bi-directional relation, we always store the "old", unchaged data, to prevent storing changes made
		// to this field. It is the responsibility of the collection to properly update it if it really changed.
		delegate.mapToMapFromEntity( newData, nonInsertableFake ? oldObj : newObj );

		for ( Map.Entry<String, Object> entry : newData.entrySet() ) {
			data.put( entry.getKey(), entry.getValue() );
		}

		return checkModified( session, newObj, oldObj );
	}
