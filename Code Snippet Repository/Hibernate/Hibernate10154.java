	private QueryBuilder(String entityName, String alias, MutableInteger aliasCounter, MutableInteger paramCounter) {
		this.entityName = entityName;
		this.alias = alias;
		this.aliasCounter = aliasCounter;
		this.paramCounter = paramCounter;

		final Parameters rootParameters = new Parameters( alias, "and", paramCounter );
		parameters.add( rootParameters );

		froms = new ArrayList<>();
		orders = new ArrayList<>();
		projections = new ArrayList<>();

		addFrom( entityName, alias, true );
	}
