	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Rev 1
		em.getTransaction().begin();

		ContainedEntity cce1 = new ContainedEntity();
		em.persist( cce1 );

		SetEntity cse1 = new SetEntity();
		cse1.getEntities().add( cce1 );
		em.persist( cse1 );

		em.getTransaction().commit();

		cce1_id = cce1.getId();
		cse1_id = cse1.getId();
	}
