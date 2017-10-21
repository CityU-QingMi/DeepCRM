	public static FetchTiming determineFetchTiming(
			FetchStyle style,
			AssociationType type,
			SessionFactoryImplementor sessionFactory) {
		switch ( style ) {
			case JOIN: {
				return FetchTiming.IMMEDIATE;
			}
			case BATCH:
			case SUBSELECT: {
				return FetchTiming.DELAYED;
			}
			default: {
				// SELECT case, can be either
				return isSubsequentSelectDelayed( type, sessionFactory )
						? FetchTiming.DELAYED
						: FetchTiming.IMMEDIATE;
			}
		}
	}
