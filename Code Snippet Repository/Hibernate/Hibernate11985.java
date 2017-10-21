	public String toInsertSql(TestDataElement testDataElement) {
		int srid = WktUtility.getSRID( testDataElement.wkt );
		String wkt = WktUtility.getWkt( testDataElement.wkt );
		return String.format(
				SQL_TEMPLATE,
				testDataElement.id,
				testDataElement.type,
				wkt,
				srid
		);
	}
