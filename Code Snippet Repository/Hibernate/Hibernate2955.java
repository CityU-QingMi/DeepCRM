	public CustomVersionOneStrategy() {
		// generate the "most significant bits" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		byte[] hiBits = new byte[8];
		// use address as first 32 bits (8 * 4 bytes)
		System.arraycopy( Helper.getAddressBytes(), 0, hiBits, 0, 4 );
		// use the "jvm identifier" as the next 32 bits
		System.arraycopy( Helper.getJvmIdentifierBytes(), 0, hiBits, 4, 4 );
		// set the version (rfc term) appropriately
		hiBits[6] &= 0x0f;
		hiBits[6] |= 0x10;

		mostSignificantBits = BytesHelper.asLong( hiBits );
	}
