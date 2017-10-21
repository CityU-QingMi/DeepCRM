	public static byte[] fromLong(long longValue) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) ( longValue >> 56 );
		bytes[1] = (byte) ( ( longValue << 8 ) >> 56 );
		bytes[2] = (byte) ( ( longValue << 16 ) >> 56 );
		bytes[3] = (byte) ( ( longValue << 24 ) >> 56 );
		bytes[4] = (byte) ( ( longValue << 32 ) >> 56 );
		bytes[5] = (byte) ( ( longValue << 40 ) >> 56 );
		bytes[6] = (byte) ( ( longValue << 48 ) >> 56 );
		bytes[7] = (byte) ( ( longValue << 56 ) >> 56 );
		return bytes;
	}
