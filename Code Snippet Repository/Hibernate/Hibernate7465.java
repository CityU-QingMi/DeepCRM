	@Test
	public void testNonAutoAppliedConvertIsNotApplied() {
		Session session = openSession();
		session.getTransaction().begin();
		session.persist( new Entity1( 1, "1" ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		session.createQuery( "delete Entity1" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
