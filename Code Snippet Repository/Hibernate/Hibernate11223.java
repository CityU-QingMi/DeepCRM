	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		SameIdTestEntity1 site1 = new SameIdTestEntity1( 1, "str1" );
		SameIdTestEntity2 site2 = new SameIdTestEntity2( 1, "str1" );

		em.persist( site1 );
		em.persist( site2 );
		em.getTransaction().commit();

		em.getTransaction().begin();
		site1 = em.find( SameIdTestEntity1.class, 1 );
		site2 = em.find( SameIdTestEntity2.class, 1 );
		site1.setStr1( "str2" );
		site2.setStr1( "str2" );
		em.getTransaction().commit();
	}
