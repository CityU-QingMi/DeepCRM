	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		country = Country.of( 123, "Germany" );
		em.persist( country );
		em.getTransaction().commit();

	}
