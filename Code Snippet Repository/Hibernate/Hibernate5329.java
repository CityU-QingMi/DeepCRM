	@Test
	public void testLookupBefore() {
		InternalClassLoader icl = new InternalClassLoader();
		Thread.currentThread().setContextClassLoader( icl );

		ClassLoaderServiceImpl csi = new ClassLoaderServiceImpl( null,
																 TcclLookupPrecedence.BEFORE );
		Class<ClassLoaderServiceImplTest> clazz = csi.classForName(
				ClassLoaderServiceImplTest.class.getName() );
		assertEquals( ClassLoaderServiceImplTest.class, clazz );
		assertEquals( 1, icl.getAccessCount() );
		csi.stop();
	}
