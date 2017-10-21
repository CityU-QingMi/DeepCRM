	private void createMetaModelClasses() {
		for ( MetaEntity entity : context.getMetaEntities() ) {
			if ( context.isAlreadyGenerated( entity.getQualifiedName() ) ) {
				continue;
			}
			context.logMessage( Diagnostic.Kind.OTHER, "Writing meta model for entity " + entity );
			ClassWriter.writeFile( entity, context );
			context.markGenerated( entity.getQualifiedName() );
		}

		// we cannot process the delayed entities in any order. There might be dependencies between them.
		// we need to process the top level entities first
		Collection<MetaEntity> toProcessEntities = context.getMetaEmbeddables();
		while ( !toProcessEntities.isEmpty() ) {
			Set<MetaEntity> processedEntities = new HashSet<MetaEntity>();
			int toProcessCountBeforeLoop = toProcessEntities.size();
			for ( MetaEntity entity : toProcessEntities ) {
				// see METAGEN-36
				if ( context.isAlreadyGenerated( entity.getQualifiedName() ) ) {
					processedEntities.add( entity );
					continue;
				}
				if ( modelGenerationNeedsToBeDeferred( toProcessEntities, entity ) ) {
					continue;
				}
				context.logMessage(
						Diagnostic.Kind.OTHER, "Writing meta model for embeddable/mapped superclass" + entity
				);
				ClassWriter.writeFile( entity, context );
				context.markGenerated( entity.getQualifiedName() );
				processedEntities.add( entity );
			}
			toProcessEntities.removeAll( processedEntities );
			if ( toProcessEntities.size() >= toProcessCountBeforeLoop ) {
				context.logMessage(
						Diagnostic.Kind.ERROR, "Potential endless loop in generation of entities."
				);
			}
		}
	}
