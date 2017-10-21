	@Test
	public void testLookupAfterAvoided() {
		InternalClassLoader icl = new InternalClassLoader();
		Thread.currentThread().setContextClassLoader( icl );

		ClassLoaderServiceImpl csi = new ClassLoaderServiceImpl( null,
																 TcclLookupPrecedence.AFTER );
		Class<ClassLoaderServiceImplTest> clazz = csi.classForName(
				ClassLoaderServiceImplTest.class.getName() );
		assertEquals( ClassLoaderServiceImplTest.class, clazz );
		assertEquals( 0, icl.getAccessCount() );
		csi.stop();
	}
