	private static Map<String, CascadeStyle> buildBaseCascadeStyleMap() {
		final HashMap<String, CascadeStyle> base = new HashMap<String, CascadeStyle>();

		base.put( "all", ALL );
		base.put( "all-delete-orphan", ALL_DELETE_ORPHAN );
		base.put( "save-update", UPDATE );
		base.put( "persist", PERSIST );
		base.put( "merge", MERGE );
		base.put( "lock", LOCK );
		base.put( "refresh", REFRESH );
		base.put( "replicate", REPLICATE );
		base.put( "evict", EVICT );
		base.put( "delete", DELETE );
		base.put( "remove", DELETE ); // adds remove as a sort-of alias for delete...
		base.put( "delete-orphan", DELETE_ORPHAN );
		base.put( "none", NONE );

		return base;
	}
