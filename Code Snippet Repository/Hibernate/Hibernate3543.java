	public static BatchingCollectionInitializerBuilder getBuilder(SessionFactoryImplementor factory) {
		switch ( factory.getSettings().getBatchFetchStyle() ) {
			case PADDED: {
				return PaddedBatchingCollectionInitializerBuilder.INSTANCE;
			}
			case DYNAMIC: {
				return DynamicBatchingCollectionInitializerBuilder.INSTANCE;
			}
			default: {
				return org.hibernate.loader.collection.plan.LegacyBatchingCollectionInitializerBuilder.INSTANCE;
				//return LegacyBatchingCollectionInitializerBuilder.INSTANCE;
			}
		}
	}
