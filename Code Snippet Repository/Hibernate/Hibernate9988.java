	@Override
	public void addIdsEqualToQuery(Parameters parameters, String prefix1, String prefix2) {
		final List<QueryParameterData> paramDatas = mapToQueryParametersFromId( null );

		final Parameters parametersToUse = getParametersToUse( parameters, paramDatas );

		for ( QueryParameterData paramData : paramDatas ) {
			parametersToUse.addWhere(
					paramData.getProperty( prefix1 ),
					false,
					"=",
					paramData.getProperty( prefix2 ),
					false
			);
		}
	}
