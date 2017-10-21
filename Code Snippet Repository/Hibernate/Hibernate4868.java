	private void applyConstraintDropping(
			Namespace namespace,
			Metadata metadata,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		final Dialect dialect = metadata.getDatabase().getJdbcEnvironment().getDialect();

		if ( !dialect.dropConstraints() ) {
			return;
		}

		for ( Table table : namespace.getTables() ) {
			if ( !table.isPhysicalTable() ) {
				continue;
			}
			if ( !schemaFilter.includeTable( table ) ) {
				continue;
			}

			final Iterator fks = table.getForeignKeyIterator();
			while ( fks.hasNext() ) {
				final ForeignKey foreignKey = (ForeignKey) fks.next();
				applySqlStrings(
						dialect.getForeignKeyExporter().getSqlDropStrings( foreignKey, metadata ),
						formatter,
						options,
						targets
				);
			}
		}
	}
