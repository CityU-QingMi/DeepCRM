	public ResultSetProcessingContextImpl(
			final ResultSet resultSet,
			final SharedSessionContractImplementor session,
			final LoadPlan loadPlan,
			final AliasResolutionContext aliasResolutionContext,
			final boolean readOnly,
			final boolean shouldUseOptionalEntityInformation,
			final boolean forceFetchLazyAttributes,
			final boolean shouldReturnProxies,
			final QueryParameters queryParameters,
			final NamedParameterContext namedParameterContext,
			final boolean hadSubselectFetches) {
		this.resultSet = resultSet;
		this.session = session;
		this.loadPlan = loadPlan;
		this.aliasResolutionContext = aliasResolutionContext;
		this.readOnly = readOnly;
		this.shouldUseOptionalEntityInformation = shouldUseOptionalEntityInformation;
		this.forceFetchLazyAttributes = forceFetchLazyAttributes;
		this.shouldReturnProxies = shouldReturnProxies;
		this.queryParameters = queryParameters;
		this.namedParameterContext = namedParameterContext;
		this.hadSubselectFetches = hadSubselectFetches;

		if ( shouldUseOptionalEntityInformation ) {
			if ( queryParameters.getOptionalId() != null ) {
				// make sure we have only one return
				if ( loadPlan.getReturns().size() > 1 ) {
					throw new IllegalStateException( "Cannot specify 'optional entity' values with multi-return load plans" );
				}
			}
		}
	}
