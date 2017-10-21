	@Test
	public void testSubclassWrongId() throws Exception {
		Mammal mammal = new Mammal();
		mammal.setMamalNbr( 2 );
		mammal.setName( "Human" );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( mammal );
		em.flush();
		Assert.assertNull( em.find( Reptile.class, 1l ) );
		em.getTransaction().rollback();
		em.close();
	}
