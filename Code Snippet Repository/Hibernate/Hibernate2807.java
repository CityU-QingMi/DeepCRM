	@Override
	public ScrollableResultsImplementor scroll(
			final QueryParameters queryParameters,
			final SharedSessionContractImplementor session) throws HibernateException {
		HolderInstantiator hi = HolderInstantiator.createClassicHolderInstantiator(
				holderConstructor,
				queryParameters.getResultTransformer()
		);
		return scroll( queryParameters, returnTypes, hi, session );
	}
