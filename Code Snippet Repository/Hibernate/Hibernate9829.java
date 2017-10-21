	public void updateCalculatedFields() {
		for ( Map.Entry<PersistentClass, ClassAuditingData> classAuditingDataEntry : persistentClassToAuditingData.entrySet() ) {
			final PersistentClass pc = classAuditingDataEntry.getKey();
			final ClassAuditingData classAuditingData = classAuditingDataEntry.getValue();
			for ( String propertyName : classAuditingData.getNonSyntheticPropertyNames() ) {
				final Property property = pc.getProperty( propertyName );
				updateCalculatedProperty( pc.getEntityName(), property, propertyName, classAuditingData );
			}
		}
	}
