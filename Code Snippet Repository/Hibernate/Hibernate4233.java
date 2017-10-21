	public ProcedureCallImpl(final SharedSessionContractImplementor session, String procedureName, Class... resultClasses) {
		super( session, new ProcedureParameterMetadata() );
		this.procedureName = procedureName;
		this.globalParameterPassNullsSetting = session.getFactory().getSessionFactoryOptions().isProcedureParameterNullPassingEnabled();

		final List<NativeSQLQueryReturn> collectedQueryReturns = new ArrayList<>();
		final Set<String> collectedQuerySpaces = new HashSet<>();

		Util.resolveResultClasses(
				new Util.ResultClassesResolutionContext() {
					@Override
					public SessionFactoryImplementor getSessionFactory() {
						return session.getFactory();
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
				resultClasses
		);

		this.queryReturns = collectedQueryReturns.toArray( new NativeSQLQueryReturn[ collectedQueryReturns.size() ] );
		this.synchronizedQuerySpaces = collectedQuerySpaces;
	}
