	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		UnversionedOptimisticLockingFieldEntity olfe = new UnversionedOptimisticLockingFieldEntity( "x" );
		em.persist( olfe );
		id1 = olfe.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		olfe = em.find( UnversionedOptimisticLockingFieldEntity.class, id1 );
		olfe.setStr( "y" );
		em.getTransaction().commit();
	}
