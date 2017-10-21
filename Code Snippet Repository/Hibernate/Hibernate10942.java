	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		StrTestEntity str1 = new StrTestEntity( "str1" );

		SetRefCollEntity coll1 = new SetRefCollEntity( 3, "coll1" );

		// Revision 1
		em.getTransaction().begin();

		em.persist( str1 );

		coll1.setCollection( new HashSet<StrTestEntity>() );
		em.persist( coll1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		str1 = em.find( StrTestEntity.class, str1.getId() );
		coll1 = em.find( SetRefCollEntity.class, coll1.getId() );

		coll1.getCollection().add( str1 );
		coll1.setData( "coll2" );

		em.getTransaction().commit();

		//

		str1_id = str1.getId();

		coll1_id = coll1.getId();
	}
