	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		// Revision 1
		em.getTransaction().begin();
		StrIntTestEntity siteComplete = new StrIntTestEntity( "data 1", 1 );
		em.persist( siteComplete );
		em.persist(
				new BabyCompleteEntity(
						babyCompleteId,
						"grandparent 1",
						"notAudited 1",
						"parent 1",
						"child 1",
						siteComplete,
						"baby 1"
				)
		);
		em.getTransaction().commit();
		siteCompleteId = siteComplete.getId();
	}
