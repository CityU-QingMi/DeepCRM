	private void test(Binder b) {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		TypedQuery<Long> q = em.createQuery( "select count(*) from Document d where d.tags = :tags", Long.class );
		b.bind( q );

		Long count = q.getSingleResult();

		em.getTransaction().commit();
		em.close();

		assertEquals( 1, count.intValue() );
	}
