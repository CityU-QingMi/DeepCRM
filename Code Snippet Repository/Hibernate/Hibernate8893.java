	@Test
	public void testNaturalIdNullValueOnPersist() {
		Session session = openSession();
		session.beginTransaction();
		C c = new C();
		session.persist( c );
		c.name = "someName";
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( c );
		session.getTransaction().commit();
		session.close();
	}
