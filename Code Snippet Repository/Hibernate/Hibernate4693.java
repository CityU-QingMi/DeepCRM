	@Deprecated
	public static String renderOrderByStringTemplate(
			String orderByFragment,
			Dialect dialect,
			SQLFunctionRegistry functionRegistry) {
		return renderOrderByStringTemplate(
				orderByFragment,
				NoOpColumnMapper.INSTANCE,
				null,
				dialect,
				functionRegistry
		);
	}
