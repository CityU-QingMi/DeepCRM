	public ProcedureCallImpl(final SharedSessionContractImplementor session, String procedureName, String... resultSetMappings) {
		super( session, new ProcedureParameterMetadata() );
		this.procedureName = procedureName;
		this.globalParameterPassNullsSetting = session.getFactory().getSessionFactoryOptions().isProcedureParameterNullPassingEnabled();

		final List<NativeSQLQueryReturn> collectedQueryReturns = new ArrayList<>();
		final Set<String> collectedQuerySpaces = new HashSet<>();

		Util.resolveResultSetMappings(
				new Util.ResultSetMappingResolutionContext() {
					@Override
					public SessionFactoryImplementor getSessionFactory() {
						return session.getFactory();
					}

					@Override
					public ResultSetMappingDefinition findResultSetMapping(String name) {
						return session.getFactory().getNamedQueryRepository().getResultSetMappingDefinition( name );
					}

					@Override
					public void addQueryReturns(NativeSQLQueryReturn... queryReturns) {
						Collections.addAll( collectedQueryReturns, queryReturns );
					}

					@Override
					public void addQuerySpaces(String... spaces) {
						Collections.addAll( collectedQuerySpaces, spaces );
					}
				},
				resultSetMappings
		);

		this.queryReturns = collectedQueryReturns.toArray( new NativeSQLQueryReturn[ collectedQueryReturns.size() ] );
		this.synchronizedQuerySpaces = collectedQuerySpaces;
	}
