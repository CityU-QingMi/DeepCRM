	public ScrollableResultsImplementor scroll(
			final QueryParameters queryParameters,
			final SharedSessionContractImplementor session) throws HibernateException {
		checkQuery( queryParameters );
		return scroll(
				queryParameters,
				queryReturnTypes,
				buildHolderInstantiator( queryParameters.getResultTransformer() ),
				session
		);
	}
