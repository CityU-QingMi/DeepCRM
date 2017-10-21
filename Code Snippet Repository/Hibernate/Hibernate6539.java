	@Test
	public void testCascadedDeleteOfChildEntitiesBug2() {
		// Relationship is one SoccerTeam to many Players.
		// Create a SoccerTeam (parent) and three Players (child).
		// Verify that the count of Players is correct.
		// Clear the SoccerTeam reference Players.
		// The orphanRemoval should remove the Players automatically.
		// @OneToMany(mappedBy="name", orphanRemoval=true)
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		SoccerTeam team = new SoccerTeam();
		int teamid = team.getId();
		Player player1 = new Player();
		player1.setName("Shalrie Joseph");
		team.addPlayer(player1);

		Player player2 = new Player();
		player2.setName("Taylor Twellman");
		team.addPlayer(player2);

		Player player3 = new Player();
		player3.setName("Steve Ralston");
		team.addPlayer(player3);
		s.persist(team);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		team = (SoccerTeam)s.merge(team);
		int count = ( (Long) s.createQuery( "select count(*) from Player" ).iterate().next() ).intValue();
		assertEquals("expected count of 3 but got = " + count, count, 3);

		// clear references to players, this should orphan the players which should
		// in turn trigger orphanRemoval logic.
		team.getPlayers().clear();
//		count = ( (Long) s.createQuery( "select count(*) from Player" ).iterate().next() ).intValue();
//		assertEquals("expected count of 0 but got = " + count, count, 0);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		count = ( (Long) s.createQuery( "select count(*) from Player" ).iterate().next() ).intValue();
		assertEquals("expected count of 0 but got = " + count, count, 0);
		tx.commit();
		s.close();
	}
