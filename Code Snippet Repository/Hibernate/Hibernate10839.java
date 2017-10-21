	@Test
	@Priority(10)
	public void initData() {
		VersionsJoinTableTestEntity uni1 = new VersionsJoinTableTestEntity( 1, "data1" );
		StrTestEntity str1 = new StrTestEntity( "str1" );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		uni1.setCollection( new HashSet<StrTestEntity>() );
		em.persist( uni1 );
		em.persist( str1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		uni1 = em.find( VersionsJoinTableTestEntity.class, uni1.getId() );
		str1 = em.find( StrTestEntity.class, str1.getId() );
		uni1.getCollection().add( str1 );

		em.getTransaction().commit();

		//

		uni1_id = uni1.getId();
		str1_id = str1.getId();
	}
