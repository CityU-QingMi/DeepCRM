	public static Object findColumnOwner(
			PersistentClass persistentClass,
			String columnName,
			MetadataBuildingContext context) {
		if ( StringHelper.isEmpty( columnName ) ) {
			//shortcut for implicit referenced column names
			return persistentClass;
		}
		PersistentClass current = persistentClass;
		Object result;
		boolean found = false;
		do {
			result = current;
			Table currentTable = current.getTable();
			try {
				context.getMetadataCollector().getPhysicalColumnName( currentTable, columnName );
				found = true;
			}
			catch (MappingException me) {
				//swallow it
			}
			Iterator joins = current.getJoinIterator();
			while ( !found && joins.hasNext() ) {
				result = joins.next();
				currentTable = ( (Join) result ).getTable();
				try {
					context.getMetadataCollector().getPhysicalColumnName( currentTable, columnName );
					found = true;
				}
				catch (MappingException me) {
					//swallow it
				}
			}
			current = current.getSuperclass();
		}
		while ( !found && current != null );
		return found ? result : null;
	}
