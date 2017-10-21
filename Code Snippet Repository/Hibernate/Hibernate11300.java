	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		// Revision 1
		em.getTransaction().begin();
		StrIntTestEntity siteMultiple = new StrIntTestEntity( "data 1", 1 );
		em.persist( siteMultiple );
		em.persist(
				new ChildMultipleParentsEntity(
						childMultipleId,
						"grandparent 1",
						"notAudited 1",
						"parent 1",
						"child 1",
						siteMultiple
				)
		);
		em.getTransaction().commit();
		siteMultipleId = siteMultiple.getId();
	}
