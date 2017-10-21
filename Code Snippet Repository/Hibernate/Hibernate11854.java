	public static Geometry from(Object object) {
		if ( object == null ) {
			return null;
		}
		try {

			if (object instanceof com.vividsolutions.jts.geom.Geometry) {
				return JTS.from( (com.vividsolutions.jts.geom.Geometry) object );
			}
			final WkbDecoder decoder = Wkb.newDecoder( Wkb.Dialect.POSTGIS_EWKB_1 );
			if ( object instanceof Blob ) {
				return decoder.decode( toByteBuffer( (Blob) object ) );
			}
			else if ( object instanceof byte[] ) {
				return decoder.decode( ByteBuffer.from( (byte[]) object ) );
			}
			else if ( object instanceof com.vividsolutions.jts.geom.Envelope ) {
				return toPolygon( JTS.from( (com.vividsolutions.jts.geom.Envelope) object ) );
			}
			else {
				throw new IllegalArgumentException(
						"Can't convert database object of type "
								+ object.getClass().getCanonicalName()
				);
			}
		}
		catch (Exception e) {
			LOGGER.warn( "Could not convert database object to a Geometry." );
			throw new HibernateException( e );
		}

	}
