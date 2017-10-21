	@Test
	@Priority(10)
	public void initData() {
		final EntityManager em = getEntityManager();

		final SetOwningEntity setOwningEntity = new SetOwningEntity( 1, "parent" );
		final SetOwnedEntity setOwnedEntity = new SetOwnedEntity( 2, "child" );

		// Revision 1: Initial persist
		em.getTransaction().begin();

		em.persist( setOwningEntity );
		em.persist( setOwnedEntity );

		em.getTransaction().commit();
		em.clear();

		ing_id = setOwningEntity.getId();
		ed_id = setOwnedEntity.getId();
	}
