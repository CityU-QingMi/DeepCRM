	public static void visitCollection(AssociationVisitationStrategy strategy, CollectionPersister persister) {
		strategy.start();
		try {
			new MetamodelGraphWalker( strategy, persister.getFactory() )
					.visitCollectionDefinition( persister );
		}
		finally {
			strategy.finish();
		}
	}
