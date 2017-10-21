	public static Geometry<?> toGeometry(Object obj) {
		byte[] raw = null;
		if ( obj == null ) {
			return null;
		}
		if ( ( obj instanceof byte[] ) ) {
			raw = (byte[]) obj;
		}
		else if ( obj instanceof Blob ) {
			raw = toByteArray( (Blob) obj );
		}
		else {
			throw new IllegalArgumentException( "Expected byte array or BLOB" );
		}

		ByteBuffer buffer = ByteBuffer.from( raw );

		WkbDecoder decoder = Wkb.newDecoder( Wkb.Dialect.HANA_EWKB );
		return decoder.decode( buffer );
	}
