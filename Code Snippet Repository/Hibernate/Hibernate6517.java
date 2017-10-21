	@Test
	@FailureExpected( jiraKey = "")
	public void testJoinAcrossEmbedded() {
		// NOTE : this may or may not work now with HHH-4883 fixed,
		// but i cannot do this checking until HHH-4599 is done.
		Session session = openSession();
		session.beginTransaction();
		session.createQuery( "from Person p join p.name.aliases a where a.source = 'FBI'" )
				.list();
		session.getTransaction().commit();
		session.close();
	}
