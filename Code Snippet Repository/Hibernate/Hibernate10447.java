	@Test
	@Priority(10)
	public void initData() {
		ele3_id = TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			EmbeddableListEntity3 ele3 = new EmbeddableListEntity3();
			ele3.getComponentList().add( new ManyToOneEagerComponent( null, "data" ) );
			entityManager.persist( ele3 );
			return ele3.getId();
		} );
	}
