	@Test
	@TestForIssue( jiraKey = "" )
	public final void testGeneratedProperty() {
		// The following block is repeated 300 times to reproduce HHH-2627.
		// Without the fix, Oracle will run out of cursors using 10g with
		// a default installation (ORA-01000: maximum open cursors exceeded).
		// The number of loops may need to be adjusted depending on the how
		// Oracle is configured.
		// Note: The block is not indented to avoid a lot of irrelevant differences.
		for ( int i=0; i<300; i++ ) {
			GeneratedPropertyEntity entity = new GeneratedPropertyEntity();
			entity.setName( "entity-1" );
			Session s = openSession();
			Transaction t = s.beginTransaction();
			s.save( entity );
			s.flush();
			assertNotNull( "no timestamp retrieved", entity.getLastModified() );
			t.commit();
			s.close();

			byte[] bytes = entity.getLastModified();

			s = openSession();
			t = s.beginTransaction();
			entity = ( GeneratedPropertyEntity ) s.get( GeneratedPropertyEntity.class, entity.getId() );
			assertTrue( BinaryType.INSTANCE.isEqual( bytes, entity.getLastModified() ) );
			t.commit();
			s.close();

			assertTrue( BinaryType.INSTANCE.isEqual( bytes, entity.getLastModified() ) );

			s = openSession();
			t = s.beginTransaction();
			s.delete( entity );
			t.commit();
			s.close();
		}
	}
