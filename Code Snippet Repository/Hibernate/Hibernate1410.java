	public static void bindQueries(
			org.hibernate.annotations.NamedQueries queriesAnn,
			MetadataBuildingContext context) {
		if ( queriesAnn == null ) {
			return;
		}

		for (org.hibernate.annotations.NamedQuery q : queriesAnn.value()) {
			bindQuery( q, context );
		}
	}
