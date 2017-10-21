	@Override
	public NativeSQLQueryPlan createQueryPlan(
			NativeSQLQuerySpecification specification,
			SessionFactoryImplementor sessionFactory) {

		CustomQuery customQuery = new SQLCustomQuery(
				specification.getQueryString(),
				specification.getQueryReturns(),
				specification.getQuerySpaces(),
				sessionFactory
		);

		return new NativeSQLQueryPlan( specification.getQueryString(), customQuery );
	}
