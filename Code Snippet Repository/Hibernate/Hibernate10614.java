	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		ClassType type = new ClassType( "type", "initial description" );
		SampleClass entity = new SampleClass();
		entity.setType( type );
		entity.setSampleValue( "initial data" );
		em.persist( type );
		em.persist( entity );
		em.getTransaction().commit();

		typeId = type.getType();
		entityId = new RelationalClassId( entity.getId(), new ClassType( "type", "initial description" ) );

		// Revision 2
		em.getTransaction().begin();
		type = em.find( ClassType.class, type.getType() );
		type.setDescription( "modified description" );
		em.merge( type );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		entity = em.find( SampleClass.class, entityId );
		entity.setSampleValue( "modified data" );
		em.merge( entity );
		em.getTransaction().commit();

		em.close();
	}
