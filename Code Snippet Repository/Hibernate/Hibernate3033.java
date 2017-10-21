	public Map<String, String> getAliasTableMap(SessionFactoryImplementor factory) {
		Map<String, String> mergedAliasTableMap = mergeAliasMaps( factory );
		if ( !mergedAliasTableMap.isEmpty() ) {
			return mergedAliasTableMap;
		}
		else if ( persistentClass != null ) {
			String table = persistentClass.getTable().getQualifiedName(
					factory.getDialect(),
					factory.getSettings().getDefaultCatalogName(),
					factory.getSettings().getDefaultSchemaName()
			);
			return Collections.singletonMap( null, table );
		}
		else {
			return Collections.emptyMap();
		}
	}
