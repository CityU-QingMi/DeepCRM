	protected void applyIndexes(
			Table table,
			TableInformation tableInformation,
			Dialect dialect,
			Metadata metadata,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		final Exporter<Index> exporter = dialect.getIndexExporter();

		final Iterator<Index> indexItr = table.getIndexIterator();
		while ( indexItr.hasNext() ) {
			final Index index = indexItr.next();
			if ( !StringHelper.isEmpty( index.getName() ) ) {
				IndexInformation existingIndex = null;
				if ( tableInformation != null ) {
					existingIndex = findMatchingIndex( index, tableInformation );
				}
				if ( existingIndex == null ) {
					applySqlStrings(
							false,
							exporter.getSqlCreateStrings( index, metadata ),
							formatter,
							options,
							targets
					);
				}
			}
		}
	}
