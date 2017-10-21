	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		EntityWithEmbeddableWithDeclaredData entity = new EntityWithEmbeddableWithDeclaredData();
		entity.setName( "Entity 1" );
		entity.setValue( new EmbeddableWithDeclaredData( 42, "TestCodeart" ) );

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist( entity );
		tx.commit();
		em.close();

		id = entity.getId();
	}
