	@Test
	public void testLookupNever() {
		InternalClassLoader icl = new InternalClassLoader();
		Thread.currentThread().setContextClassLoader( icl );

		ClassLoaderServiceImpl csi = new ClassLoaderServiceImpl( null,
																 TcclLookupPrecedence.NEVER );
		try {
			csi.classForName( "test.class.name" );
			assertTrue( false );
		}
		catch ( Exception e ) {
		}
		assertEquals( 0, icl.getAccessCount() );
		csi.stop();
	}
