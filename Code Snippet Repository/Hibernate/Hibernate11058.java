	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		PropertiesTestEntity pte = new PropertiesTestEntity( "x" );
		em.persist( pte );
		id1 = pte.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		pte = em.find( PropertiesTestEntity.class, id1 );
		pte.setStr( "y" );
		em.getTransaction().commit();
	}
