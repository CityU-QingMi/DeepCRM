	public static void processDynamicFilterParameters(
			final String sqlFragment,
			final ParameterContainer container,
			final HqlSqlWalker walker) {
		if ( walker.getEnabledFilters().isEmpty()
				&& ( !hasDynamicFilterParam( sqlFragment ) )
				&& ( !( hasCollectionFilterParam( sqlFragment ) ) ) ) {
			return;
		}

		Dialect dialect = walker.getSessionFactoryHelper().getFactory().getDialect();
		String symbols = ParserHelper.HQL_SEPARATORS + dialect.openQuote() + dialect.closeQuote();
		StringTokenizer tokens = new StringTokenizer( sqlFragment, symbols, true );
		StringBuilder result = new StringBuilder();

		while ( tokens.hasMoreTokens() ) {
			final String token = tokens.nextToken();
			if ( token.startsWith( ParserHelper.HQL_VARIABLE_PREFIX ) ) {
				final String filterParameterName = token.substring( 1 );
				final String[] parts = LoadQueryInfluencers.parseFilterParameterName( filterParameterName );
				final FilterImpl filter = (FilterImpl) walker.getEnabledFilters().get( parts[0] );
				final Object value = filter.getParameter( parts[1] );
				final Type type = filter.getFilterDefinition().getParameterType( parts[1] );
				final String typeBindFragment = StringHelper.join(
						",",
						ArrayHelper.fillArray(
								"?",
								type.getColumnSpan( walker.getSessionFactoryHelper().getFactory() )
						)
				);
				final String bindFragment;
				if ( value != null && Collection.class.isInstance( value ) ) {
					bindFragment = StringHelper.join(
							",",
							ArrayHelper.fillArray( typeBindFragment, ( (Collection) value ).size() )
					);
				}
				else {
					bindFragment = typeBindFragment;
				}
				result.append( bindFragment );
				container.addEmbeddedParameter( new DynamicFilterParameterSpecification( parts[0], parts[1], type ) );
			}
			else {
				result.append( token );
			}
		}

		container.setText( result.toString() );
	}
