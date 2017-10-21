	@Test
	public void testVersionUnchangedPrimitiveByteArray() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		VersionedCompiledCode cc = createCompiledCode();
		int codeSize = 5;
		byte[] full = new byte[codeSize];
		for ( int i = 0; i < codeSize; i++ ) {
			full[i] = ( byte ) ( 1 + i );
		}
		cc.setFullCode( full );
		s.persist( cc );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		VersionedCompiledCode recompiled = getCompiledCodeClass().cast( s.get( getCompiledCodeClass(), getId( cc ) ) );
		assertEquals( recompiled.getFullCode()[codeSize - 1], cc.getFullCode()[codeSize - 1] );
		assertEquals( recompiled.getVersion(), Integer.valueOf( 0 ) );
		s.flush();
		assertEquals( recompiled.getVersion(), Integer.valueOf( 0 ) );
		s.delete( recompiled );
		tx.commit();
		s.close();
	}
