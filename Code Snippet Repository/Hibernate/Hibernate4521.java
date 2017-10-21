	@Override
	@SuppressWarnings("")
	public Set<QueryParameter<?>> collectAllParameters() {
		if ( hasNamedParameters() || hasPositionalParameters() ) {
			final HashSet allParameters = new HashSet();
			allParameters.addAll( namedDescriptorMap.values() );
			allParameters.addAll( ArrayHelper.toList( ordinalDescriptors ) );
			return allParameters;
		}

		return Collections.emptySet();
	}
