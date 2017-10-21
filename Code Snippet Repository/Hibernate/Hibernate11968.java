	private void initializeSpatialTestSupport(ServiceRegistry serviceRegistry) {
		try {
			TestSupport support = TestSupportFactories.instance().getTestSupportFactory( getDialect() );
			dataSourceUtils = support.createDataSourceUtil( serviceRegistry );
			expectationsFactory = support.createExpectationsFactory( dataSourceUtils );
			testData = support.createTestData( this );
			geometryEquality = support.createGeometryEquality();
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}
	}
