	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Rev 1
		em.getTransaction().begin();
		ParentEntity pe = new ParentEntity( "x" );
		em.persist( pe );
		id1 = pe.getId();
		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();
		pe = em.find( ParentEntity.class, id1 );
		pe.setData( "y" );
		em.getTransaction().commit();
	}
