	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		ParametrizedCustomTypeEntity pcte = new ParametrizedCustomTypeEntity();

		// Revision 1 (persisting 1 entity)
		em.getTransaction().begin();

		pcte.setStr( "U" );

		em.persist( pcte );

		em.getTransaction().commit();

		// Revision 2 (changing the value)
		em.getTransaction().begin();

		pcte = em.find( ParametrizedCustomTypeEntity.class, pcte.getId() );

		pcte.setStr( "V" );

		em.getTransaction().commit();

		//

		pcte_id = pcte.getId();
	}
