	@Test
	public void testLookupWhereBeanFactoryYieldsNonDataSourceType() throws Exception {
		final BeanFactory beanFactory = mock(BeanFactory.class);

		given(beanFactory.getBean(DATASOURCE_BEAN_NAME, DataSource.class)).willThrow(
				new BeanNotOfRequiredTypeException(DATASOURCE_BEAN_NAME,
						DataSource.class, String.class));

		try {
				BeanFactoryDataSourceLookup lookup = new BeanFactoryDataSourceLookup(beanFactory);
				lookup.getDataSource(DATASOURCE_BEAN_NAME);
				fail("should have thrown DataSourceLookupFailureException");
		}
		catch (DataSourceLookupFailureException ex) { /* expected */ }
	}
