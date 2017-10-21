	private String extractDetails(LoadPlan loadPlan) {
		switch ( loadPlan.getDisposition() ) {
			case MIXED: {
				return "mixed";
			}
			case ENTITY_LOADER: {
				return "entity=" + ( (EntityReturn) loadPlan.getReturns().get( 0 ) ).getEntityPersister().getEntityName();
			}
			case COLLECTION_INITIALIZER: {
				return "collection=" + ( (CollectionReturn) loadPlan.getReturns().get( 0 ) ).getCollectionPersister().getRole();
			}
			default: {
				return "???";
			}
		}
	}
