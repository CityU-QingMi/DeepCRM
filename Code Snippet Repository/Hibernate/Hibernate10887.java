	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		mto_id1 = 1;
		type_id1 = 2;
		type_id2 = 3;

		// Rev 1
		// Preparing the types
		em.getTransaction().begin();

		NotInsertableEntityType type1 = new NotInsertableEntityType( type_id1, "type1" );
		NotInsertableEntityType type2 = new NotInsertableEntityType( type_id2, "type2" );

		em.persist( type1 );
		em.persist( type2 );

		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();

		ManyToOneNotInsertableEntity master = new ManyToOneNotInsertableEntity( mto_id1, type_id1, type1 );
		em.persist( master );

		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();

		master = em.find( ManyToOneNotInsertableEntity.class, mto_id1 );
		master.setNumber( type_id2 );

		em.getTransaction().commit();
	}
