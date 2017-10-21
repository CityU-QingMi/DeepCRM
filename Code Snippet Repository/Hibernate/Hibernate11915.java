	public static Polygon toPolygon(Envelope env, int srid) {
		final Coordinate[] coords = new Coordinate[5];

		coords[0] = new Coordinate( env.getMinX(), env.getMinY() );
		coords[1] = new Coordinate( env.getMinX(), env.getMaxY() );
		coords[2] = new Coordinate( env.getMaxX(), env.getMaxY() );
		coords[3] = new Coordinate( env.getMaxX(), env.getMinY() );
		coords[4] = new Coordinate( env.getMinX(), env.getMinY() );
		final LinearRing shell = geomFactory.createLinearRing( coords );

		final Polygon pg = geomFactory.createPolygon( shell, null );
		pg.setSRID( srid );
		return pg;
	}
