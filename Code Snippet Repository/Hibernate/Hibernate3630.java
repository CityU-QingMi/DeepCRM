	public static BatchingEntityLoaderBuilder getBuilder(SessionFactoryImplementor factory) {
		switch ( factory.getSettings().getBatchFetchStyle() ) {
			case PADDED: {
				return PaddedBatchingEntityLoaderBuilder.INSTANCE;
			}
			case DYNAMIC: {
				return DynamicBatchingEntityLoaderBuilder.INSTANCE;
			}
			default: {
				return org.hibernate.loader.entity.plan.LegacyBatchingEntityLoaderBuilder.INSTANCE;
//				return LegacyBatchingEntityLoaderBuilder.INSTANCE;
			}
		}
	}
