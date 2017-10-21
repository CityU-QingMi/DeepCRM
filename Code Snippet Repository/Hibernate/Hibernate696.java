	private void handleIdentifierValueBinding(
			KeyValue identifierValueBinding,
			Dialect dialect,
			String defaultCatalog,
			String defaultSchema,
			RootClass entityBinding) {
		// todo : store this result (back into the entity or into the KeyValue, maybe?)
		// 		This process of instantiating the id-generator is called multiple times.
		//		It was done this way in the old code too, so no "regression" here; but
		//		it could be done better
		try {
			final IdentifierGenerator ig = identifierValueBinding.createIdentifierGenerator(
					getIdentifierGeneratorFactory(),
					dialect,
					defaultCatalog,
					defaultSchema,
					entityBinding
			);

			if ( ig instanceof ExportableProducer ) {
				( (ExportableProducer) ig ).registerExportables( getDatabase() );
			}
		}
		catch (MappingException e) {
			// ignore this for now.  The reasoning being "non-reflective" binding as needed
			// by tools.  We want to hold off requiring classes being present until we
			// try to build a SF.  Here, just building the Metadata, it is "ok" for an
			// exception to occur, the same exception will happen later as we build the SF.
			log.debugf( "Ignoring exception thrown when trying to build IdentifierGenerator as part of Metadata building", e );
		}
	}
