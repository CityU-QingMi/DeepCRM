	@Test
	public void testConverterIsNotIncorrectlyApplied() {
		Widget w = new Widget();
		w.setId( 1 );
		w.setDimension( new BigDecimal( "1.0" ) );
		w.setCost( new Money( "2.0" ) );

		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist( w );
		em.getTransaction().commit();
		em.close();		

		em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Widget recorded = em.find( Widget.class, 1 );
		assertEquals( 1, recorded.getId() );

		em.remove(recorded);
		em.getTransaction().commit();
		em.close();
	}
