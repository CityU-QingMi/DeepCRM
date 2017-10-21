	public Point getTestPoint() {
		WKTReader reader = new WKTReader();
		try {
			Point point = (Point) reader.read( TEST_POINT_WKT );
			point.setSRID( getTestSrid() );
			return point;
		}
		catch (ParseException e) {
			throw new RuntimeException( e );
		}
	}
