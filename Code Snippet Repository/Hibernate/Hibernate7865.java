	@Test
	@TestForIssue(jiraKey = "")
	public void testUpdateTimestampGeneration() {
		Session s = openSession();
		s.beginTransaction();
		TheEntity theEntity = new TheEntity( 1 );
		assertNull( theEntity.updated );
		s.save( theEntity );

		//TODO: Actually the value should be non-null after save
		assertNull( theEntity.updated );
		s.getTransaction().commit();
		s.close();

		Timestamp created = theEntity.vmCreatedSqlTimestamp;
		Timestamp updated = theEntity.updated;
		assertNotNull( updated );
		assertNotNull( created );

		s = openSession();
		s.beginTransaction();

		theEntity = (TheEntity) s.get( TheEntity.class, 1 );
		theEntity.lastName = "Smith";
		try {
			Thread.sleep( 1 );
		}
		catch (InterruptedException ignore) {}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		theEntity = (TheEntity) s.get( TheEntity.class, 1 );

		assertEquals( "Creation timestamp should not change on update", created, theEntity.vmCreatedSqlTimestamp );
		assertTrue( "Update timestamp should have changed due to update", theEntity.updated.after( updated ) );

		s.delete( theEntity );
		s.getTransaction().commit();
		s.close();
	}
