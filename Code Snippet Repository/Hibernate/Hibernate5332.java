	@Test
	public void testLookupAfterNotFound() {
		InternalClassLoader icl = new InternalClassLoader();
		Thread.currentThread().setContextClassLoader( icl );

		ClassLoaderServiceImpl csi = new ClassLoaderServiceImpl( null,
																 TcclLookupPrecedence.BEFORE );
		try {
			csi.classForName( "test.class.not.found" );
			assertTrue( false );
		}
		catch ( Exception e ) {
		}
		assertEquals( 0, icl.getAccessCount() );
		csi.stop();
	}
