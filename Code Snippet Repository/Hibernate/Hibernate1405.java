	public static void bindNativeQueries(
			NamedNativeQueries queriesAnn,
			MetadataBuildingContext context,
			boolean isDefault) {
		if ( queriesAnn == null ) {
			return;
		}

		for (NamedNativeQuery q : queriesAnn.value()) {
			bindNativeQuery( q, context, isDefault );
		}
	}
