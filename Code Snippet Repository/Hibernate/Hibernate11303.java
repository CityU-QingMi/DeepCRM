	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		// Revision 1
		em.getTransaction().begin();
		StrIntTestEntity siteSingle = new StrIntTestEntity( "data 1", 1 );
		em.persist( siteSingle );
		em.persist(
				new ChildSingleParentEntity(
						childSingleId,
						"grandparent 1",
						"notAudited 1",
						"parent 1",
						"child 1",
						siteSingle
				)
		);
		em.getTransaction().commit();
		siteSingleId = siteSingle.getId();
		em.close();
	}
