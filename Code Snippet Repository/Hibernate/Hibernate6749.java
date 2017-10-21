	@Test
	public void testBlob() throws Exception {

		C cc = createCompiledCode();
		Byte[] header = new Byte[2];
		header[0] = new Byte( ( byte ) 3 );
		header[1] = new Byte( ( byte ) 0 );
		cc.setHeader( header );
		int codeSize = 5;
		byte[] full = new byte[codeSize];
		for ( int i = 0; i < codeSize; i++ ) {
			full[i] = ( byte ) ( 1 + i );
		}
		cc.setFullCode( full );

		doInHibernate( this::sessionFactory, session -> {
			session.persist( cc );
		} );

		doInHibernate( this::sessionFactory, session -> {
			C recompiled = getCompiledCodeClass().cast( session.get( getCompiledCodeClass(), getId( cc ) ) );
			assertEquals( recompiled.getHeader()[1], cc.getHeader()[1] );
			assertEquals( recompiled.getFullCode()[codeSize - 1], cc.getFullCode()[codeSize - 1] );
		} );
	}
