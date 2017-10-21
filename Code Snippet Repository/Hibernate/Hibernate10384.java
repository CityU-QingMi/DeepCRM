	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		StrTestEntity emptyEntity = new StrTestEntity( "" );
		em.persist( emptyEntity );
		StrTestEntity nullEntity = new StrTestEntity( null );
		em.persist( nullEntity );
		em.getTransaction().commit();

		emptyId = emptyEntity.getId();
		nullId = nullEntity.getId();

		em.close();
		em = getEntityManager();

		// Should not generate revision after NULL to "" modification and vice versa on Oracle.
		em.getTransaction().begin();
		emptyEntity = em.find( StrTestEntity.class, emptyId );
		emptyEntity.setStr( null );
		em.merge( emptyEntity );
		nullEntity = em.find( StrTestEntity.class, nullId );
		nullEntity.setStr( "" );
		em.merge( nullEntity );
		em.getTransaction().commit();

		em.close();
	}
