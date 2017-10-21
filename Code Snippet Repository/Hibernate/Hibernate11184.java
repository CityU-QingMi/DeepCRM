	@Test
	@Priority(10)
	public void initData() throws InterruptedException {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		StrTestEntity ste = new StrTestEntity( "x" );
		em.persist( ste );
		steId = ste.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		ste = em.find( StrTestEntity.class, steId );
		ste.setStr( "y" );
		em.getTransaction().commit();
	}
