	@Test
	public void testFindBySQLDiscriminatedSameSession() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		for ( Object entity : session.createQuery( "from A" ).list() ) {
			session.delete( entity );
		}
		A savedA = new A();
		session.save(savedA);

		B savedB = new B();
		session.save(savedB);
		session.flush();

		Query query = session.createSQLQuery( "select identifier_column as {a.id}, clazz_discriminata as {a.class}, name as {a.name}, count_ as {a.count} from TA {a}" )
				.addEntity( "a", A.class );
		List list = query.list();

		assertNotNull(list);
		assertEquals(2, list.size());

		A a1 = (A) list.get(0);
		A a2 = (A) list.get(1);

		assertTrue((a2 instanceof B) || (a1 instanceof B));
		assertFalse( a1 instanceof B && a2 instanceof B );

		if (a1 instanceof B) {
			assertSame(a1, savedB);
			assertSame(a2, savedA);
		}
		else {
			assertSame(a2, savedB);
			assertSame(a1, savedA);
		}

		session.clear();
		List list2 = session.getNamedQuery("propertyResultDiscriminator").list();
		assertEquals(2, list2.size());
		
		session.getTransaction().commit();
		session.close();
	}
