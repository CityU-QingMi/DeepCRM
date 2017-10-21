	private HolderInstantiator buildHolderInstantiator(ResultTransformer queryLocalResultTransformer) {
		final ResultTransformer implicitResultTransformer = aggregatedSelectExpression == null
				? null
				: aggregatedSelectExpression.getResultTransformer();
		return HolderInstantiator.getHolderInstantiator(
				implicitResultTransformer,
				queryLocalResultTransformer,
				queryReturnAliases
		);
	}
