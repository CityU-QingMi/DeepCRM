	@Test
	@SkipForDialect( SybaseDialect.class )
	public void testBinary() throws Exception {

		C cc = createCompiledCode();
		byte[] metadata = new byte[2];
		metadata[0] = ( byte ) 3;
		metadata[1] = ( byte ) 0;
		cc.setMetadata( metadata );

		doInHibernate( this::sessionFactory, session -> {
			session.persist( cc );
		} );

		doInHibernate( this::sessionFactory, session -> {
			C recompiled = getCompiledCodeClass().cast( session.get( getCompiledCodeClass(), getId( cc ) ) );
			assertEquals( recompiled.getMetadata()[1], cc.getMetadata()[1] );
		} );
	}
