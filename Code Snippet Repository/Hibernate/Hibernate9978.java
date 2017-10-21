	@Override
	public boolean mapToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		data.put( propertyData.getName(), newObj );
		boolean dbLogicallyDifferent = true;
		if ( (session.getFactory().getJdbcServices()
				.getDialect() instanceof Oracle8iDialect) && (newObj instanceof String || oldObj instanceof String) ) {
			// Don't generate new revision when database replaces empty string with NULL during INSERT or UPDATE statements.
			dbLogicallyDifferent = !(StringTools.isEmpty( newObj ) && StringTools.isEmpty( oldObj ));
		}
		return dbLogicallyDifferent && !EqualsHelper.areEqual( newObj, oldObj );
	}
