	@Test
	public void testJpqlParametrizedBetween() {
		final EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		final Query query = em.createQuery( "from Employee where salary between :low and :high" );

		query.setParameter( "low", new Float( 300f ) );
		query.setParameter( "high", new Float( 400f ) );
		assertEquals( 0, query.getResultList().size() );

		query.setParameter( "low", new Float( 100f ) );
		query.setParameter( "high", new Float( 200f ) );
		assertEquals( 0, query.getResultList().size() );

		query.setParameter( "low", new Float( 200f ) );
		query.setParameter( "high", new Float( 300f ) );
		assertEquals( 1, query.getResultList().size() );

		em.getTransaction().commit();
		em.close();
	}
