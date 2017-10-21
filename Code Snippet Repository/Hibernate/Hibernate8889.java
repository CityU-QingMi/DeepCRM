	@Test
	public void testNaturalIdRecachingWhenNeeded() {
		Session session = openSession();
		session.getSessionFactory().getStatistics().clear();
		session.beginTransaction();
		Another it = new Another( "it");
		session.save( it );
		Serializable id = it.getId();
		session.getTransaction().commit();
		session.close();
		
		session = openSession();
		for (int i=0; i < 10; i++) {
			session.beginTransaction();
			it = session.byId(Another.class).load(id);
			if (i == 9) {
				it.setName("name" + i);
			}
			it.setSurname("surname" + i); // changing something but not the natural-id's
			session.getTransaction().commit();
		}
		
		session = openSession();
		session.beginTransaction();
		it = session.bySimpleNaturalId(Another.class).load("it");
		assertNull(it);
		assertEquals( 0, session.getSessionFactory().getStatistics().getNaturalIdCacheHitCount() );
		it = session.byId(Another.class).load(id);
		session.delete(it);
		session.getTransaction().commit();
		session.close();
		
		// finally there should be only 2 NaturalIdCache puts : 1. insertion, 2. when updating natural-id from 'it' to 'name9'
		assertEquals( 2, session.getSessionFactory().getStatistics().getNaturalIdCachePutCount() );
	}
