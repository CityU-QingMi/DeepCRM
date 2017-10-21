	protected List executeLoad(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters,
			LoadQueryDetails loadQueryDetails,
			boolean returnProxies,
			ResultTransformer forcedResultTransformer) throws SQLException {
		final List<AfterLoadAction> afterLoadActions = new ArrayList<AfterLoadAction>();
		return executeLoad(
				session,
				queryParameters,
				loadQueryDetails,
				returnProxies,
				forcedResultTransformer,
				afterLoadActions
		);
	}
