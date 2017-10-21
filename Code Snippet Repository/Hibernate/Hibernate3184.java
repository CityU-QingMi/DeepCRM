	public static long asLong(byte[] bytes) {
		if ( bytes == null ) {
			return 0;
		}
		if ( bytes.length != 8 ) {
			throw new IllegalArgumentException( "Expecting 8 byte values to construct a long" );
		}
		long value = 0;
		for (int i=0; i<8; i++) {
			value = (value << 8) | (bytes[i] & 0xff);
		}
		return value;
	}
