	public void performValidation(
			Metadata metadata,
			DatabaseInformation databaseInformation,
			ExecutionOptions options,
			Dialect dialect) {
		for ( Namespace namespace : metadata.getDatabase().getNamespaces() ) {
			if ( schemaFilter.includeNamespace( namespace ) ) {
				validateTables( metadata, databaseInformation, options, dialect, namespace );
			}
		}

		for ( Namespace namespace : metadata.getDatabase().getNamespaces() ) {
			if ( schemaFilter.includeNamespace( namespace ) ) {
				for ( Sequence sequence : namespace.getSequences() ) {
					if ( schemaFilter.includeSequence( sequence ) ) {
						final SequenceInformation sequenceInformation = databaseInformation.getSequenceInformation(
								sequence.getName()
						);
						validateSequence( sequence, sequenceInformation );
					}
				}
			}
		}
	}
