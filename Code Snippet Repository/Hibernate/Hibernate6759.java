	@Test
	@TestForIssue( jiraKey = "")
	public void testVersionUnchangedByteArray() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		VersionedCompiledCode cc = createCompiledCode();
		Byte[] header = new Byte[2];
		header[0] = new Byte( ( byte ) 3 );
		header[1] = new Byte( ( byte ) 0 );
		cc.setHeader( header );
		s.persist( cc );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		VersionedCompiledCode recompiled = getCompiledCodeClass().cast( s.get( getCompiledCodeClass(), getId( cc ) ) );
		assertEquals( recompiled.getHeader()[1], cc.getHeader()[1] );
		assertEquals( recompiled.getVersion(), Integer.valueOf( 0 ) );
		s.flush();
		assertEquals( recompiled.getVersion(), Integer.valueOf( 0 ) );
		s.delete( recompiled );
		tx.commit();
		s.close();
	}
