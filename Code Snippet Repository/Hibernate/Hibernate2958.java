	public static void main(String[] args) throws UnknownHostException {
		byte[] addressBytes = InetAddress.getLocalHost().getAddress();
		System.out.println( "Raw ip address bytes : " + addressBytes.toString() );

		int addressInt = BytesHelper.toInt( addressBytes );
		System.out.println( "ip address int : " + addressInt );

		String formatted = Integer.toHexString( addressInt );
		StringBuilder buf = new StringBuilder( "00000000" );
		buf.replace( 8 - formatted.length(), 8, formatted );
		String addressHex = buf.toString();
		System.out.println( "ip address hex : " + addressHex );
	}
