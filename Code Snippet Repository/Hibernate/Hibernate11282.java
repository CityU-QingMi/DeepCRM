	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		MixedOverrideEntity mixedEntity = new MixedOverrideEntity( "data 1", 1, "data 2" );
		em.persist( mixedEntity );
		em.getTransaction().commit();
		mixedEntityId = mixedEntity.getId();

		mixedTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.MixedOverrideEntity_AUD"
		).getTable();
	}
