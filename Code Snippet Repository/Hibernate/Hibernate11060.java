	@Test
	public void testProxyIdentifier() {
		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		ListRefEdEntity listReferencedEntity1 = em.getReference(
				ListRefEdEntity.class, id_ListRefEdEntity1
		);

		assert listReferencedEntity1 instanceof HibernateProxy;

		// Revision 3
		ListRefIngEntity refingEntity3 = new ListRefIngEntity(
				Integer.valueOf( 3 ), "refing3", listReferencedEntity1
		);

		em.persist( refingEntity3 );

		listReferencedEntity1.getReffering().size();
		em.getTransaction().commit();
	}
