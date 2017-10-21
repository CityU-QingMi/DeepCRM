	@Override
	public void addNamedIdEqualsToQuery(Parameters parameters, String prefix1, IdMapper mapper, boolean equals) {
		final List<QueryParameterData> paramDatas1 = mapToQueryParametersFromId( null );
		final List<QueryParameterData> paramDatas2 = mapper.mapToQueryParametersFromId( null );

		final Parameters parametersToUse = getParametersToUse( parameters, paramDatas1 );
		final Iterator<QueryParameterData> paramDataIter1 = paramDatas1.iterator();
		final Iterator<QueryParameterData> paramDataIter2 = paramDatas2.iterator();
		while ( paramDataIter1.hasNext() ) {
			final QueryParameterData paramData1 = paramDataIter1.next();
			final QueryParameterData paramData2 = paramDataIter2.next();
			parametersToUse.addWhereWithNamedParam(
					paramData1.getProperty( prefix1 ),
					equals ? "=" : "<>",
					paramData2.getQueryParameterName()
			);
		}
	}
