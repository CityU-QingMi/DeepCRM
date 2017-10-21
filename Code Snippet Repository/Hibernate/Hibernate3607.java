	public ScrollableResultsImplementor scroll(final QueryParameters queryParameters, final SharedSessionContractImplementor session)
			throws HibernateException {

		ResultTransformer resultTransformer = queryParameters.getResultTransformer();

		HolderInstantiator holderInstantiator = ( resultTransformer == null ) ?
				HolderInstantiator.NOOP_INSTANTIATOR :
				new HolderInstantiator( resultTransformer, this::getReturnAliasesForTransformer );

		return scroll(
				queryParameters,
				resultTypes,
				holderInstantiator,
				session
		);
	}
