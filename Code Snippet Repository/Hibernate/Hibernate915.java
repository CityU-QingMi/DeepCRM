	private static void bindCustomSql(
			MappingDocument sourceDocument,
			EntitySource entitySource,
			PersistentClass entityDescriptor) {
		if ( entitySource.getCustomSqlInsert() != null ) {
			entityDescriptor.setCustomSQLInsert(
					entitySource.getCustomSqlInsert().getSql(),
					entitySource.getCustomSqlInsert().isCallable(),
					entitySource.getCustomSqlInsert().getCheckStyle()
			);
		}

		if ( entitySource.getCustomSqlUpdate() != null ) {
			entityDescriptor.setCustomSQLUpdate(
					entitySource.getCustomSqlUpdate().getSql(),
					entitySource.getCustomSqlUpdate().isCallable(),
					entitySource.getCustomSqlUpdate().getCheckStyle()
			);
		}

		if ( entitySource.getCustomSqlDelete() != null ) {
			entityDescriptor.setCustomSQLDelete(
					entitySource.getCustomSqlDelete().getSql(),
					entitySource.getCustomSqlDelete().isCallable(),
					entitySource.getCustomSqlDelete().getCheckStyle()
			);
		}

		entityDescriptor.setLoaderName( entitySource.getCustomLoaderName() );
	}
