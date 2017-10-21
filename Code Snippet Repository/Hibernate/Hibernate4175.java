	private void associateSubclassNamesToSubclassTableIndexes(
			PersistentClass persistentClass,
			Set<String> classNames,
			String[][] mapping,
			SessionFactoryImplementor factory) {

		final String tableName = persistentClass.getTable().getQualifiedName(
				factory.getDialect(),
				factory.getSettings().getDefaultCatalogName(),
				factory.getSettings().getDefaultSchemaName()
		);

		associateSubclassNamesToSubclassTableIndex( tableName, classNames, mapping );

		Iterator itr = persistentClass.getJoinIterator();
		while ( itr.hasNext() ) {
			final Join join = (Join) itr.next();
			final String secondaryTableName = join.getTable().getQualifiedName(
					factory.getDialect(),
					factory.getSettings().getDefaultCatalogName(),
					factory.getSettings().getDefaultSchemaName()
			);
			associateSubclassNamesToSubclassTableIndex( secondaryTableName, classNames, mapping );
		}
	}
