	protected void applyUniqueKeys(
			Table table,
			TableInformation tableInfo,
			Dialect dialect,
			Metadata metadata,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		if ( uniqueConstraintStrategy == null ) {
			uniqueConstraintStrategy = determineUniqueConstraintSchemaUpdateStrategy( metadata );
		}

		if ( uniqueConstraintStrategy != UniqueConstraintSchemaUpdateStrategy.SKIP ) {
			final Exporter<Constraint> exporter = dialect.getUniqueKeyExporter();

			final Iterator ukItr = table.getUniqueKeyIterator();
			while ( ukItr.hasNext() ) {
				final UniqueKey uniqueKey = (UniqueKey) ukItr.next();
				// Skip if index already exists. Most of the time, this
				// won't work since most Dialects use Constraints. However,
				// keep it for the few that do use Indexes.
				IndexInformation indexInfo = null;
				if ( tableInfo != null && StringHelper.isNotEmpty( uniqueKey.getName() ) ) {
					indexInfo = tableInfo.getIndex( Identifier.toIdentifier( uniqueKey.getName() ) );
				}
				if ( indexInfo == null ) {
					if ( uniqueConstraintStrategy == UniqueConstraintSchemaUpdateStrategy.DROP_RECREATE_QUIETLY ) {
						applySqlStrings(
								true,
								exporter.getSqlDropStrings( uniqueKey, metadata ),
								formatter,
								options,
								targets
						);
					}

					applySqlStrings(
							true,
							exporter.getSqlCreateStrings( uniqueKey, metadata ),
							formatter,
							options,
							targets
					);
				}
			}
		}
	}
