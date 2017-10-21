	@Test
	public void testGenericGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		SoundSystem system = new SoundSystem();
		system.setBrand( "Genelec" );
		system.setModel( "T234" );
		Furniture fur = new Furniture();
		s.persist( system );
		s.persist( fur );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		system = ( SoundSystem ) s.get( SoundSystem.class, system.getId() );
		fur = ( Furniture ) s.get( Furniture.class, fur.getId() );
		assertNotNull( system );
		assertNotNull( fur );
		s.delete( system );
		s.delete( fur );
		tx.commit();
		s.close();

	}
