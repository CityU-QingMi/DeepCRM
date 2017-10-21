	@Test
	public void test() {
		doInHibernate( this::sessionFactory, session -> {
			BuildingList buildingList = new BuildingList();
			buildingList.setName( "ABC" );
			session.persist( buildingList );

			BLEHome home = new BLEHome();
			home.setHasCtv( 123 );
			home.setList( buildingList );
			buildingList.getEntries().add( home );
			session.persist( home );

			BLENonLiving nonLiving = new BLENonLiving();
			nonLiving.setDelayed( true );
			nonLiving.setList( buildingList );
			buildingList.getEntries().add( nonLiving );
			session.persist( nonLiving );
		} );
		doInHibernate( this::sessionFactory, session -> {
			List<BuildingList> buildingLists = session.createQuery( "from BuildingList" ).getResultList();
			BuildingList buildingList = buildingLists.get( 0 );
			assertEquals(2, buildingList.getEntries().size());
		});
	}
