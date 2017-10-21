	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		ListRefEdEntity listReferencedEntity1 = new ListRefEdEntity(
				Integer.valueOf( 1 ), "str1"
		);
		ListRefIngEntity refingEntity1 = new ListRefIngEntity(
				Integer.valueOf( 1 ), "refing1", listReferencedEntity1
		);

		// Revision 1
		em.getTransaction().begin();
		em.persist( listReferencedEntity1 );
		em.persist( refingEntity1 );
		em.getTransaction().commit();

		id_ListRefEdEntity1 = listReferencedEntity1.getId();

		// Revision 2
		ListRefIngEntity refingEntity2 = new ListRefIngEntity(
				Integer.valueOf( 2 ), "refing2", listReferencedEntity1
		);

		em.getTransaction().begin();
		em.persist( refingEntity2 );
		em.getTransaction().commit();
	}
