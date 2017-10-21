	private QueryBuilder(QueryBuilder other) {
		this.entityName = other.entityName;
		this.alias = other.alias;
		this.aliasCounter = other.aliasCounter.deepCopy();
		this.paramCounter = other.paramCounter.deepCopy();
		for (final Parameters params : other.parameters) {
			this.parameters.add( params.deepCopy() );
		}

		froms = new ArrayList<>( other.froms );
		orders = new ArrayList<>( other.orders );
		projections = new ArrayList<>( other.projections );
	}
