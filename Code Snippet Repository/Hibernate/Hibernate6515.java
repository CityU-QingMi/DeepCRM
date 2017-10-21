	@Test
	public void testJoinAcrossEmbedded() {
		Session session = openSession();
		session.beginTransaction();
		session.createQuery( "from Person p join p.address as a join a.country as c where c.name = 'US'" )
				.list();
		session.createQuery( "from Person p join p.address as a join a.country as c where c.id = 'US'" )
				.list();
		session.getTransaction().commit();
		session.close();
	}
