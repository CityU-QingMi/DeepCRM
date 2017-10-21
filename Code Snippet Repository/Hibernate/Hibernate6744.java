	@Test
	public void testBasic() throws Exception {
		// set up data...
		Session s = openSession( );
		Transaction tx = s.beginTransaction();
		Team t = new Team();
		Player p = new Player();
		p.setName( "me" );
		t.getPlayers().add( p );
		p.setTeam( t );
		s.persist(p);
		s.persist( t );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Team t2 = s.load( Team.class, t.getId() );
		Set<Player> players = t2.getPlayers();
		Iterator<Player> iterator = players.iterator();
		assertEquals( "me", iterator.next().getName() );
		tx.commit();
		s.close();

		// clean up data
		s = openSession();
		tx = s.beginTransaction();
		t = s.get( Team.class, t2.getId() );
		p = s.get( Player.class, p.getId() );
		s.delete( p );
		s.delete( t );
		tx.commit();
		s.close();
	}
