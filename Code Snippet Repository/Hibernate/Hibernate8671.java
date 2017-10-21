	@Test
	public void testFindBySQLDiscriminatedDiffSession() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		for ( Object entity : session.createQuery( "from A" ).list() ) {
			session.delete( entity );
		}
		A savedA = new A();
		session.save(savedA);

		B savedB = new B();
		session.save(savedB);
		session.getTransaction().commit();
		int count = session.createQuery("from A").list().size();
		session.close();

		session = openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery( "select identifier_column as {a.id}, clazz_discriminata as {a.class}, count_ as {a.count}, name as {a.name} from TA" )
				.addEntity( "a", A.class );
		List list = query.list();

		assertNotNull(list);
		assertEquals(count, list.size());
		session.getTransaction().commit();
		session.close();
	}
