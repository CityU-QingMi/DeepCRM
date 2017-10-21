	@Test
	@SuppressWarnings({ "", "" })
	public void getDataSourceWhereSuppliedMapHasNonDataSourceTypeUnderSpecifiedKey() throws Exception {
		Map dataSources = new HashMap();
		dataSources.put(DATA_SOURCE_NAME, new Object());
		MapDataSourceLookup lookup = new MapDataSourceLookup(dataSources);

		exception.expect(ClassCastException.class);
		lookup.getDataSource(DATA_SOURCE_NAME);
	}
