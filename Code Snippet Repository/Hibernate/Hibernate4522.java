	@Override
	@SuppressWarnings("")
	public Set<Parameter<?>> collectAllParametersJpa() {
		if ( hasNamedParameters() || hasPositionalParameters() ) {
			final HashSet allParameters = new HashSet();
			allParameters.addAll( namedDescriptorMap.values() );
			allParameters.addAll( ArrayHelper.toList( ordinalDescriptors ) );
			return allParameters;
		}

		return Collections.emptySet();
	}
