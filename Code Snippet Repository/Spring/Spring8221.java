	@Nullable
	public static DataSource retrieveDataSource(TestContext testContext, @Nullable String name) {
		Assert.notNull(testContext, "TestContext must not be null");
		BeanFactory bf = testContext.getApplicationContext().getAutowireCapableBeanFactory();

		try {
			// look up by type and explicit name
			if (StringUtils.hasText(name)) {
				return bf.getBean(name, DataSource.class);
			}
		}
		catch (BeansException ex) {
			logger.error(
				String.format("Failed to retrieve DataSource named '%s' for test context %s", name, testContext), ex);
			throw ex;
		}

		try {
			if (bf instanceof ListableBeanFactory) {
				ListableBeanFactory lbf = (ListableBeanFactory) bf;

				// look up single bean by type
				Map<String, DataSource> dataSources = BeanFactoryUtils.beansOfTypeIncludingAncestors(lbf,
					DataSource.class);
				if (dataSources.size() == 1) {
					return dataSources.values().iterator().next();
				}

				try {
					// look up single bean by type, with support for 'primary' beans
					return bf.getBean(DataSource.class);
				}
				catch (BeansException ex) {
					logBeansException(testContext, ex, PlatformTransactionManager.class);
				}
			}

			// look up by type and default name
			return bf.getBean(DEFAULT_DATA_SOURCE_NAME, DataSource.class);
		}
		catch (BeansException ex) {
			logBeansException(testContext, ex, DataSource.class);
			return null;
		}
	}
