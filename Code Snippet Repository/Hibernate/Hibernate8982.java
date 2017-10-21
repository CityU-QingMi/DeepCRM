	@Test
	public void testMultiLoadWithCacheModeIgnore() {
		// do the multi-load, telling Hibernate to IGNORE the L2 cache -
		//		the end result should be that the cache is (still) empty afterwards
		Session session = openSession();
		session.getTransaction().begin();
		List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class )
				.with( CacheMode.IGNORE )
				.multiLoad( ids(56) );
		session.getTransaction().commit();
		session.close();

		assertEquals( 56, list.size() );
		for ( SimpleEntity entity : list ) {
			assertFalse( sessionFactory().getCache().containsEntity( SimpleEntity.class, entity.getId() ) );
		}
	}
