	@Test
	@TestForIssue( jiraKey = "" )
	public void testSingularAttributeAccessByName() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		SingularAttribute soldDate_ = em.getMetamodel().embeddable( ShelfLife.class )
				.getSingularAttribute( "soldDate" );
		assertEquals( java.sql.Date.class, soldDate_.getBindableJavaType());
		assertEquals( java.sql.Date.class, soldDate_.getType().getJavaType() );
		assertEquals( java.sql.Date.class, soldDate_.getJavaType() );

		em.getTransaction().commit();
		em.close();
	}
