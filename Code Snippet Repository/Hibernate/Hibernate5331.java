	@Test
	public void testLookupAfter() {
		InternalClassLoader icl = new InternalClassLoader();
		Thread.currentThread().setContextClassLoader( icl );

		ClassLoaderServiceImpl csi = new ClassLoaderServiceImpl( null,
																 TcclLookupPrecedence.AFTER );
		try {
			csi.classForName( "test.class.name" );
			assertTrue( false );
		}
		catch ( Exception e ) {
		}
		assertEquals( 0, icl.getAccessCount() );
		csi.stop();
	}
