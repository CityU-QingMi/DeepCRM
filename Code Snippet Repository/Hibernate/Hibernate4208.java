	public static void visitEntity(AssociationVisitationStrategy strategy, EntityPersister persister) {
		strategy.start();
		try {
			new MetamodelGraphWalker( strategy, persister.getFactory() )
					.visitEntityDefinition( persister );
		}
		finally {
			strategy.finish();
		}
	}
