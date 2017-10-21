	@Test
	public void testNullTCCL() {
		Thread.currentThread().setContextClassLoader( null );

		ClassLoaderServiceImpl csi1 = new ClassLoaderServiceImpl( null,
																  TcclLookupPrecedence.BEFORE );
		Class<ClassLoaderServiceImplTest> clazz1 = csi1.classForName(
				ClassLoaderServiceImplTest.class.getName() );
		assertEquals( ClassLoaderServiceImplTest.class, clazz1 );
		csi1.stop();

		ClassLoaderServiceImpl csi2 = new ClassLoaderServiceImpl( null,
																  TcclLookupPrecedence.AFTER );
		Class<ClassLoaderServiceImplTest> clazz2 = csi2.classForName(
				ClassLoaderServiceImplTest.class.getName() );
		assertEquals( ClassLoaderServiceImplTest.class, clazz2 );
		csi2.stop();

		ClassLoaderServiceImpl csi3 = new ClassLoaderServiceImpl( null,
																  TcclLookupPrecedence.NEVER );
		Class<ClassLoaderServiceImplTest> clazz3 = csi3.classForName(
				ClassLoaderServiceImplTest.class.getName() );
		assertEquals( ClassLoaderServiceImplTest.class, clazz3 );
		csi3.stop();
	}
