	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		EmbeddableMapEntity eme1 = new EmbeddableMapEntity();
		EmbeddableMapEntity eme2 = new EmbeddableMapEntity();

		// Revision 1 (eme1: initialy empty, eme2: initialy 1 mapping)
		em.getTransaction().begin();
		eme2.getComponentMap().put( "1", c3_1 );
		em.persist( eme1 );
		em.persist( eme2 );
		em.getTransaction().commit();

		// Revision 2 (eme1: adding 2 mappings, eme2: no changes)
		em.getTransaction().begin();
		eme1 = em.find( EmbeddableMapEntity.class, eme1.getId() );
		eme2 = em.find( EmbeddableMapEntity.class, eme2.getId() );
		eme1.getComponentMap().put( "1", c3_1 );
		eme1.getComponentMap().put( "2", c3_2 );
		em.getTransaction().commit();

		// Revision 3 (eme1: removing an existing mapping, eme2: replacing a value)
		em.getTransaction().begin();
		eme1 = em.find( EmbeddableMapEntity.class, eme1.getId() );
		eme2 = em.find( EmbeddableMapEntity.class, eme2.getId() );
		eme1.getComponentMap().remove( "1" );
		eme2.getComponentMap().put( "1", c3_2 );
		em.getTransaction().commit();

		// No revision (eme1: removing a non-existing mapping, eme2: replacing with the same value)
		em.getTransaction().begin();
		eme1 = em.find( EmbeddableMapEntity.class, eme1.getId() );
		eme2 = em.find( EmbeddableMapEntity.class, eme2.getId() );
		eme1.getComponentMap().remove( "3" );
		eme2.getComponentMap().put( "1", c3_2 );
		em.getTransaction().commit();

		eme1_id = eme1.getId();
		eme2_id = eme2.getId();

		em.close();
	}
