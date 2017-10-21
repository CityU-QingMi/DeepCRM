	private Map<String, String> mergeAliasMaps(SessionFactoryImplementor factory) {
		Map<String, String> ret = new HashMap<String, String>();
		if ( aliasTableMap != null ) {
			ret.putAll( aliasTableMap );
		}
		if ( aliasEntityMap != null ) {
			for ( Map.Entry<String, String> entry : aliasEntityMap.entrySet() ) {
				ret.put(
						entry.getKey(),
						Joinable.class.cast( factory.getEntityPersister( entry.getValue() ) ).getTableName()
				);
			}
		}
		return ret;
	}
