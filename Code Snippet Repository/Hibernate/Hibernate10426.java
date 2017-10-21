	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getOrCreateEntityManager();
		try {
			// revision 1
			EnumMapEntity entity = new EnumMapEntity();
			entity.getTypes().put( EnumMapEntity.EnumType.TYPE_A, new EnumMapType( "A" ) );
			entity.getTypes().put( EnumMapEntity.EnumType.TYPE_B, new EnumMapType( "B" ) );
			em.getTransaction().begin();
			em.persist( entity );
			em.getTransaction().commit();
			// revision 2
			em.getTransaction().begin();
			entity = em.find( EnumMapEntity.class, entity.getId() );
			entity.getTypes().remove( EnumMapEntity.EnumType.TYPE_A );
			entity.getTypes().put( EnumMapEntity.EnumType.TYPE_C, new EnumMapType( "C" ) );
			em.getTransaction().commit();
			entityId = entity.getId();
		}
		finally {
			em.close();
		}
	}
