	@Test
	@Priority(10)
	public void initData() {
		NamingTestEntity2 nte1 = new NamingTestEntity2("data1" );
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist( nte1 );
		em.getTransaction().commit();
		this.id = nte1.getId();
	}
