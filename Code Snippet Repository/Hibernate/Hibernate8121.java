	private void check(
			ReturnMetadata returnMetadata,
	        boolean expectingEmptyTypes,
	        boolean expectingEmptyAliases) {
		assertNotNull( "null return metadata", returnMetadata );
		assertNotNull( "null return metadata - types", returnMetadata );
		assertEquals( "unexpected return size", 1, returnMetadata.getReturnTypes().length );
		if ( expectingEmptyTypes ) {
			assertNull( "non-empty types", returnMetadata.getReturnTypes()[0] );
		}
		else {
			assertNotNull( "empty types", returnMetadata.getReturnTypes()[0] );
		}
		if ( expectingEmptyAliases ) {
			assertNull( "non-empty aliases", returnMetadata.getReturnAliases() );
		}
		else {
			assertNotNull( "empty aliases", returnMetadata.getReturnAliases() );
			assertNotNull( "empty aliases", returnMetadata.getReturnAliases()[0] );
		}
	}
