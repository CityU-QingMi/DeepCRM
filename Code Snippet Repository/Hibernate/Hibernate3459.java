	public TupleBuilderTransformer(org.hibernate.Query hqlQuery) {
		final Type[] resultTypes = hqlQuery.getReturnTypes();
		final int tupleSize = resultTypes.length;

		this.tupleElements = CollectionHelper.arrayList( tupleSize );

		final String[] aliases = hqlQuery.getReturnAliases();
		final boolean hasAliases = aliases != null && aliases.length > 0;
		this.tupleElementsByAlias = hasAliases
				? CollectionHelper.<String, HqlTupleElementImpl>mapOfSize( tupleSize )
				: Collections.<String, HqlTupleElementImpl>emptyMap();

		for ( int i = 0; i < tupleSize; i++ ) {
			final HqlTupleElementImpl tupleElement = new HqlTupleElementImpl(
					i,
					aliases == null ? null : aliases[i],
					resultTypes[i]
			);
			tupleElements.add( tupleElement );
			if ( hasAliases ) {
				final String alias = aliases[i];
				if ( alias != null ) {
					tupleElementsByAlias.put( alias, tupleElement );
				}
			}
		}
	}
