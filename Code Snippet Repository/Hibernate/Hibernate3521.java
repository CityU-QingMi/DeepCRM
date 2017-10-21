	public List doQueryAndInitializeNonLazyCollections(
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final boolean returnProxies) throws HibernateException, SQLException {
		return doQueryAndInitializeNonLazyCollections(
				session,
				queryParameters,
				returnProxies,
				null
		);
	}
