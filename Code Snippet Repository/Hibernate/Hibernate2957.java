	public static void main(String[] args) {
		CustomVersionOneStrategy strategy = new CustomVersionOneStrategy();

		for ( int i = 0; i < 1000; i++ ) {
			System.out.println( "Generation # " + i + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
			byte[] loBits = new byte[8];

			long sysTime = System.currentTimeMillis();
			short hiTime = (short) ( System.currentTimeMillis() >>> 32 );
			int loTime = (int) sysTime;
			System.arraycopy( BytesHelper.fromShort( hiTime ), 0, loBits, 0, 2 );
			System.arraycopy( BytesHelper.fromInt( loTime ), 0, loBits, 2, 4 );
			System.arraycopy( Helper.getCountBytes(), 0, loBits, 6, 2 );

			System.out.println( "    before bit setting ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
			System.out.println( "       loBits[0] : " + BytesHelper.toBinaryString( loBits[0] ) );
			System.out.println( "             lsb : " + BytesHelper.toBinaryString( BytesHelper.asLong( loBits ) ) );
			System.out.println( "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );

			loBits[0] &= 0x3f;
			loBits[0] |= ((byte)2 << (byte)6);

			System.out.println( "    after bit setting ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
			System.out.println( "       loBits[0] : " + BytesHelper.toBinaryString( loBits[0] ) );
			long leastSignificantBits = BytesHelper.asLong( loBits );
			System.out.println( "             lsb : " + BytesHelper.toBinaryString( leastSignificantBits ) );
			System.out.println( "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );


			UUID uuid = new UUID( strategy.mostSignificantBits, leastSignificantBits );
			System.out.println( "  uuid : " + uuid.toString() );
			System.out.println( "  variant : " + uuid.variant() );
			System.out.println( "  version : " + uuid.version() );
			if ( uuid.variant() != 2 ) {
				throw new RuntimeException( "bad variant" );
			}
			System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		}
	}
