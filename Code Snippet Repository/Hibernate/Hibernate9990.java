	@Override
	public void addIdEqualsToQuery(Parameters parameters, Object id, String alias, String prefix, boolean equals) {
		final List<QueryParameterData> paramDatas = mapToQueryParametersFromId( id );

		final Parameters parametersToUse = getParametersToUse( parameters, paramDatas );

		for ( QueryParameterData paramData : paramDatas ) {
			if ( paramData.getValue() == null ) {
				handleNullValue( parametersToUse, alias, paramData.getProperty( prefix ), equals );
			}
			else if ( alias == null ) {
				parametersToUse.addWhereWithParam(
						paramData.getProperty( prefix ),
						equals ? "=" : "<>",
						paramData.getValue()
				);
			}
			else {
				parametersToUse.addWhereWithParam(
						alias,
						paramData.getProperty( prefix ),
						equals ? "=" : "<>",
						paramData.getValue()
				);
			}
		}
	}
