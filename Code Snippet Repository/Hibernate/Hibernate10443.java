	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		EmbeddableListEntity1 ele1 = new EmbeddableListEntity1();

		// Revision 1 (ele1: initially 1 element in both collections)
		em.getTransaction().begin();
		ele1.getComponentList().add( c3_1 );
		em.persist( ele1 );
		em.getTransaction().commit();

		// Revision (still 1) (ele1: removing non-existing element)
		em.getTransaction().begin();
		ele1 = em.find( EmbeddableListEntity1.class, ele1.getId() );
		ele1.getComponentList().remove( c3_2 );
		em.getTransaction().commit();

		// Revision 2 (ele1: adding one element)
		em.getTransaction().begin();
		ele1 = em.find( EmbeddableListEntity1.class, ele1.getId() );
		ele1.getComponentList().add( c3_2 );
		em.getTransaction().commit();

		// Revision 3 (ele1: adding one existing element)
		em.getTransaction().begin();
		ele1 = em.find( EmbeddableListEntity1.class, ele1.getId() );
		ele1.getComponentList().add( c3_1 );
		em.getTransaction().commit();

		// Revision 4 (ele1: removing one existing element)
		em.getTransaction().begin();
		ele1 = em.find( EmbeddableListEntity1.class, ele1.getId() );
		ele1.getComponentList().remove( c3_2 );
		em.getTransaction().commit();

		ele1_id = ele1.getId();

		em.close();
	}
