	public static long generateLeastSignificantBits(long seed) {
		byte[] loBits = new byte[8];

		short hiTime = (short) ( seed >>> 32 );
		int loTime = (int) seed;
		System.arraycopy( BytesHelper.fromShort( hiTime ), 0, loBits, 0, 2 );
		System.arraycopy( BytesHelper.fromInt( loTime ), 0, loBits, 2, 4 );
		System.arraycopy( Helper.getCountBytes(), 0, loBits, 6, 2 );
		loBits[0] &= 0x3f;
		loBits[0] |= ((byte)2 << (byte)6);

		return BytesHelper.asLong( loBits );
	}
