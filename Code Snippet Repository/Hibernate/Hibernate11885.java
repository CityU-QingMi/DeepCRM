	private Object toNative(Geometry geom, Connection connection) {
		try {
			final SDOGeometry sdoGeom = Encoders.encode( geom );
			return store( sdoGeom, connection );
		}
		catch (SQLException e) {
			throw new HibernateException( "Problem during conversion from JTS to SDOGeometry", e );
		}
		catch (IllegalArgumentException e) {
			//we get here if the type of geometry is unsupported by geolatte encoders
			throw new HibernateException( e.getMessage() );
		}
		catch(Exception e) {
			throw new HibernateException( e );
		}
	}
