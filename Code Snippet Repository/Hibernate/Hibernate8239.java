	@Test
	@TestForIssue( jiraKey = "" )
	public void testGenerationPastBound() {
		Session session = openSession();
		session.getTransaction().begin();
		for (int i = 0; i < 100; i++) {
			TheEntity entity = new TheEntity( Integer.toString( i ) );
			session.save( entity );
		}
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		TheEntity number100 = session.get( TheEntity.class, 100 );
		assertThat( number100, notNullValue() );
		session.createQuery( "delete TheEntity" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
