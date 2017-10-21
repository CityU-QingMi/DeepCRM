	@Test
	public void testIdClass() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Sickness sick = new Sickness();

			sick.setClassification( "H1N1" );
			sick.setType( "Flu" );
			em.persist( sick );
			em.flush();
			Sickness.PK id = new Sickness.PK();
			id.setClassification( sick.getClassification() );
			id.setType( sick.getType() );
			assertEquals( id, em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( sick ) );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
