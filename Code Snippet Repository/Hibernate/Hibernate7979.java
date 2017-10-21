	@Test
	@TestForIssue( jiraKey = "" )
	@RequiresDialect( H2Dialect.class )
	public void testEmptyInList() {
		Session session = openSession();
		session.beginTransaction();
		Human human = new Human();
		human.setName( new Name( "Lukasz", null, "Antoniak" ) );
		human.setNickName( "NONE" );
		session.save( human );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from Human h where h.nickName in ()" ).list();
		assertEquals( 0, results.size() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( human );
		session.getTransaction().commit();
		session.close();
	}
