	void withStandardServiceRegistry(boolean globalQuoting, boolean skipColumnDefinitions, TestWork work) {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, globalQuoting )
				.applySetting( AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS_SKIP_COLUMN_DEFINITIONS, skipColumnDefinitions )
				.build();

		try {
			work.doTestWork( ssr );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
