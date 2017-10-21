	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		PropertyAccessTypeEntity pate = new PropertyAccessTypeEntity( "data" );
		em.persist( pate );
		id1 = pate.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		pate = em.find( PropertyAccessTypeEntity.class, id1 );
		pate.writeData( "data2" );
		em.getTransaction().commit();
	}
