	@Test
	@TestForIssue( jiraKey = "" )
	public void testStatementCaching() {
		Session session = openSession();
		session.beginTransaction();

		//save 2 new entities, one valid, one invalid (neither should be persisted)
		IrrelevantEntity irrelevantEntity = new IrrelevantEntity();
		irrelevantEntity.setName( "valid 1" );
		session.save( irrelevantEntity );
		//name is required
		irrelevantEntity = new IrrelevantEntity();
		session.save( irrelevantEntity );
		try {
			session.flush();
			Assert.fail( "Validation exception did not occur" );
		}
		catch (Exception e) {
			//this is expected roll the transaction back
			session.getTransaction().rollback();
		}
		session.close();

		session = openSession();
		session.beginTransaction();

		//save a new entity and commit it
		irrelevantEntity = new IrrelevantEntity();
		irrelevantEntity.setName( "valid 2" );
		session.save( irrelevantEntity );
		session.flush();
		session.getTransaction().commit();
		session.close();

		//only one entity should have been inserted to the database (if the statement in the cache wasn't cleared then it would have inserted both entities)
		session = openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria( IrrelevantEntity.class );
		List results = criteria.list();
		session.getTransaction().commit();
		session.close();

		Assert.assertEquals( 1, results.size() );
	}
