	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		ScalePrecisionEntity entity = new ScalePrecisionEntity( 13.0 );
		em.persist( entity );
		em.getTransaction().commit();

		id = entity.getId();
		auditTable = metadata().getEntityBinding( "org.hibernate.envers.test.integration.basic.ScalePrecisionEntity_AUD" )
				.getTable();
		originalTable = metadata().getEntityBinding( "org.hibernate.envers.test.integration.basic.ScalePrecisionEntity" )
				.getTable();
	}
