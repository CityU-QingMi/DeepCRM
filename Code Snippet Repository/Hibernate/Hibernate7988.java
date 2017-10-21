	@Test
	public void testInsertWithGeneratedId() {
		// Make sure the env supports bulk inserts with generated ids...
		if ( !supportsBulkInsertIdGeneration( PettingZoo.class ) ) {
			SkipLog.reportSkip(
					"bulk id generation not supported",
					"test bulk inserts with generated id and generated timestamp"
			);
			return;
		}

		// create a Zoo
		Zoo zoo = new Zoo();
		zoo.setName( "zoo" );

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.save( zoo );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		int count = s.createQuery( "insert into PettingZoo (name) select name from Zoo" ).executeUpdate();
		t.commit();
		s.close();

		assertEquals( "unexpected insertion count", 1, count );

		s = openSession();
		t = s.beginTransaction();
		PettingZoo pz = ( PettingZoo ) s.createQuery( "from PettingZoo" ).uniqueResult();
		t.commit();
		s.close();

		assertEquals( zoo.getName(), pz.getName() );
		assertTrue( !zoo.getId().equals( pz.getId() ) );

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete Zoo" ).executeUpdate();
		t.commit();
		s.close();
	}
