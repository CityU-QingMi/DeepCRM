	public static String renderOrderByStringTemplate(
			String orderByFragment,
			final ColumnMapper columnMapper,
			final SessionFactoryImplementor sessionFactory,
			final Dialect dialect,
			final SQLFunctionRegistry functionRegistry) {
		return translateOrderBy(
				orderByFragment,
				columnMapper,
				sessionFactory,
				dialect,
				functionRegistry
		).injectAliases( LEGACY_ORDER_BY_ALIAS_RESOLVER );
	}
