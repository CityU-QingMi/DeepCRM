	public static OrderByTranslation translateOrderBy(
			String orderByFragment,
			final ColumnMapper columnMapper,
			final SessionFactoryImplementor sessionFactory,
			final Dialect dialect,
			final SQLFunctionRegistry functionRegistry) {
		TranslationContext context = new TranslationContext() {
			public SessionFactoryImplementor getSessionFactory() {
				return sessionFactory;
			}

			public Dialect getDialect() {
				return dialect;
			}

			public SQLFunctionRegistry getSqlFunctionRegistry() {
				return functionRegistry;
			}

			public ColumnMapper getColumnMapper() {
				return columnMapper;
			}
		};

		return OrderByFragmentTranslator.translate( context, orderByFragment );
	}
