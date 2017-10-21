	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		DateTestEntity dte = new DateTestEntity( new Date( 12345000 ) );
		em.persist( dte );
		id1 = dte.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		dte = em.find( DateTestEntity.class, id1 );
		dte.setDateValue( new Date( 45678000 ) );
		em.getTransaction().commit();
	}
