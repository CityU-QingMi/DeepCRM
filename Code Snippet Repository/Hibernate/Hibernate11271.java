	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		ClassOverrideAuditedEntity classOverrideAuditedEntity = new ClassOverrideAuditedEntity( "data 1", 1, "data 2" );
		em.persist( classOverrideAuditedEntity );
		em.getTransaction().commit();
		classAuditedEntityId = classOverrideAuditedEntity.getId();

		// Revision 2
		em.getTransaction().begin();
		ClassOverrideNotAuditedEntity classOverrideNotAuditedEntity = new ClassOverrideNotAuditedEntity(
				"data 1",
				1,
				"data 2"
		);
		em.persist( classOverrideNotAuditedEntity );
		em.getTransaction().commit();
		classNotAuditedEntityId = classOverrideNotAuditedEntity.getId();

		classAuditedTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.ClassOverrideAuditedEntity_AUD"
		).getTable();
		classNotAuditedTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.ClassOverrideNotAuditedEntity_AUD"
		).getTable();
	}
