	protected void applyForeignKeys(
			Table table,
			TableInformation tableInformation,
			Dialect dialect,
			Metadata metadata,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		if ( dialect.hasAlterTable() ) {
			final Exporter<ForeignKey> exporter = dialect.getForeignKeyExporter();

			@SuppressWarnings("unchecked")
			final Iterator<ForeignKey> fkItr = table.getForeignKeyIterator();
			while ( fkItr.hasNext() ) {
				final ForeignKey foreignKey = fkItr.next();
				if ( foreignKey.isPhysicalConstraint() && foreignKey.isCreationEnabled() ) {
					boolean existingForeignKeyFound = false;
					if ( tableInformation != null ) {
						existingForeignKeyFound = checkForExistingForeignKey(
								foreignKey,
								tableInformation
						);
					}
					if ( !existingForeignKeyFound ) {
						// todo : shouldn't we just drop+recreate if FK exists?
						//		this follows the existing code from legacy SchemaUpdate which just skipped

						// in old SchemaUpdate code, this was the trigger to "create"
						applySqlStrings(
								false,
								exporter.getSqlCreateStrings( foreignKey, metadata ),
								formatter,
								options,
								targets
						);
					}
				}
			}
		}
	}
