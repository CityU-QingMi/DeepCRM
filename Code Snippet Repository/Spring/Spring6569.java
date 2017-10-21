	@Test(expected = DataSourceLookupFailureException.class)
	public void testNoDataSourceAtJndiLocation() throws Exception {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup() {
			@Override
			protected <T> T lookup(String jndiName, Class<T> requiredType) throws NamingException {
				assertEquals(DATA_SOURCE_NAME, jndiName);
				throw new NamingException();
			}
		};
		lookup.getDataSource(DATA_SOURCE_NAME);
	}
