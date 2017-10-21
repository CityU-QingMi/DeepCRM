	@Test
	public void testUpdateOnAnimal() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();
		int count = s.createQuery( "update Animal set description = description where description = :desc" )
				.setString( "desc", data.frog.getDescription() )
				.executeUpdate();
		assertEquals( "Incorrect entity-updated count", 1, count );

		count = s.createQuery( "update Animal set description = :newDesc where description = :desc" )
				.setString( "desc", data.polliwog.getDescription() )
				.setString( "newDesc", "Tadpole" )
				.executeUpdate();
		assertEquals( "Incorrect entity-updated count", 1, count );

		Animal tadpole = ( Animal ) s.load( Animal.class, data.polliwog.getId() );
		assertEquals( "Update did not take effect", "Tadpole", tadpole.getDescription() );

		count = s.createQuery( "update Animal set bodyWeight = bodyWeight + :w1 + :w2" )
				.setDouble( "w1", 1 )
				.setDouble( "w2", 2 )
				.executeUpdate();
		assertEquals( "incorrect count on 'complex' update assignment", count, 6 );

		if ( ! ( getDialect() instanceof MySQLDialect ) ) {
			// MySQL does not support (even un-correlated) subqueries against the update-mutating table
			s.createQuery( "update Animal set bodyWeight = ( select max(bodyWeight) from Animal )" )
					.executeUpdate();
		}

		t.commit();
		s.close();

		data.cleanup();
	}
