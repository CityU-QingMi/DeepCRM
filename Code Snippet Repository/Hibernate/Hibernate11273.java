	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		PropertyOverrideEntity propertyEntity = new PropertyOverrideEntity( "data 1", 1, "data 2" );
		em.persist( propertyEntity );
		em.getTransaction().commit();
		propertyEntityId = propertyEntity.getId();

		// Revision 2
		em.getTransaction().begin();
		TransitiveOverrideEntity transitiveEntity = new TransitiveOverrideEntity( "data 1", 1, "data 2", 2, "data 3" );
		em.persist( transitiveEntity );
		em.getTransaction().commit();
		transitiveEntityId = transitiveEntity.getId();

		// Revision 3
		em.getTransaction().begin();
		AuditedSpecialEntity auditedEntity = new AuditedSpecialEntity( "data 1", 1, "data 2" );
		em.persist( auditedEntity );
		em.getTransaction().commit();
		auditedEntityId = auditedEntity.getId();

		propertyTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.PropertyOverrideEntity_AUD"
		).getTable();
		transitiveTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.TransitiveOverrideEntity_AUD"
		).getTable();
		auditedTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditoverride.AuditedSpecialEntity_AUD"
		).getTable();
	}
