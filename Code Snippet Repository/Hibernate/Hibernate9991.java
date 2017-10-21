	@Override
	public void addNamedIdEqualsToQuery(Parameters parameters, String prefix, boolean equals) {
		final List<QueryParameterData> paramDatas = mapToQueryParametersFromId( null );

		final Parameters parametersToUse = getParametersToUse( parameters, paramDatas );

		for ( QueryParameterData paramData : paramDatas ) {
			parametersToUse.addWhereWithNamedParam(
					paramData.getProperty( prefix ),
					equals ? "=" : "<>",
					paramData.getQueryParameterName()
			);
		}
	}
