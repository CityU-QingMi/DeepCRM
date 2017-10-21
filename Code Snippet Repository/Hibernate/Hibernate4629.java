	private static CustomLoaderExtension buildSpecializedCustomLoader(final ResultContext context) {
		// might be better to just manually construct the Return(s).. SQLQueryReturnProcessor does a lot of
		// work that is really unnecessary here.
		final SQLQueryReturnProcessor processor = new SQLQueryReturnProcessor(
				context.getQueryReturns(),
				context.getSession().getFactory()
		);
		processor.process();
		final List<org.hibernate.loader.custom.Return> customReturns = processor.generateCustomReturns( false );

		CustomQuery customQuery = new CustomQuery() {
			@Override
			public String getSQL() {
				return context.getSql();
			}

			@Override
			public Set<String> getQuerySpaces() {
				return context.getSynchronizedQuerySpaces();
			}

			@Override
			public Map getNamedParameterBindPoints() {
				// no named parameters in terms of embedded in the SQL string
				return null;
			}

			@Override
			public List<org.hibernate.loader.custom.Return> getCustomQueryReturns() {
				return customReturns;
			}
		};

		return new CustomLoaderExtension(
				customQuery,
				context.getQueryParameters(),
				context.getSession()
		);
	}
