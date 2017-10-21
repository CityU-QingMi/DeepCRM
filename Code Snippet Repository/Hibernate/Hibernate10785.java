	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		CompositeCustomTypeEntity ccte = new CompositeCustomTypeEntity();

		// Revision 1 (persisting 1 entity)
		em.getTransaction().begin();

		ccte.setComponent( new Component( "a", 1 ) );

		em.persist( ccte );

		em.getTransaction().commit();

		// Revision 2 (changing the component)
		em.getTransaction().begin();

		ccte = em.find( CompositeCustomTypeEntity.class, ccte.getId() );

		ccte.getComponent().setProp1( "b" );

		em.getTransaction().commit();

		// Revision 3 (replacing the component)
		em.getTransaction().begin();

		ccte = em.find( CompositeCustomTypeEntity.class, ccte.getId() );

		ccte.setComponent( new Component( "c", 3 ) );

		em.getTransaction().commit();

		//

		ccte_id = ccte.getId();
	}
