	public Map<Integer, Geometry> expectedGeoms(String type, TestData testData) {
		Map<Integer, Geometry> result = new HashMap<Integer, Geometry>();
		WktDecoder decoder = Wkt.newDecoder();
		for ( TestDataElement testDataElement : testData ) {
			if ( testDataElement.type.equalsIgnoreCase( type ) ) {
				try {
					result.put( testDataElement.id, decoder.decode( testDataElement.wkt ) );
				}
				catch (WktDecodeException e) {
					System.out
							.println(
									String.format(
											"Parsing WKT fails for case %d : %s",
											testDataElement.id,
											testDataElement.wkt
									)
							);
					throw new RuntimeException( e );
				}
			}
		}
		return result;
	}
