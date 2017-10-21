	public JPAIndexHolder(Index index) {
		StringTokenizer tokenizer = new StringTokenizer( index.columnList(), "," );
		List<String> tmp = new ArrayList<String>();
		while ( tokenizer.hasMoreElements() ) {
			tmp.add( tokenizer.nextToken().trim() );
		}
		this.name = index.name();
		this.columns = new String[tmp.size()];
		this.ordering = new String[tmp.size()];
		this.unique = index.unique();
		initializeColumns( columns, ordering, tmp );
	}
