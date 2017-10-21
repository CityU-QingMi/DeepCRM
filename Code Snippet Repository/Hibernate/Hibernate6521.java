	@Test
	public void testCascadedDeleteOfChildOneToOne() {
		// create two single player teams (for one versus one match of soccer)
		// and associate teams with players via the special OneVOne methods.
		// Clear the Team reference to players, which should orphan the teams.
		// Orphaning the team should delete the team. 

		Session s = openSession();
		Transaction tx = s.beginTransaction();

		SoccerTeam team = new SoccerTeam();
		team.setName("Shalrie's team");
		Player player1 = new Player();
		player1.setName("Shalrie Joseph");
		team.setOneVonePlayer(player1);
		player1.setOneVoneTeam(team);

		s.persist(team);

		SoccerTeam team2 = new SoccerTeam();
		team2.setName("Taylor's team");
		Player player2 = new Player();
		player2.setName("Taylor Twellman");
		team2.setOneVonePlayer(player2);
		player2.setOneVoneTeam(team2);
		s.persist(team2);
		tx.commit();

		tx = s.beginTransaction();
		s.clear();
		team2 = (SoccerTeam)s.load(team2.getClass(), team2.getId());
		team = (SoccerTeam)s.load(team.getClass(), team.getId());
		int count = ( (Long) s.createQuery( "select count(*) from Player" ).iterate().next() ).intValue();
		assertEquals("expected count of 2 but got = " + count, count, 2);

		// clear references to players, this should orphan the players which should
		// in turn trigger orphanRemoval logic.
		team.setOneVonePlayer(null);
		team2.setOneVonePlayer(null);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		count = ( (Long) s.createQuery( "select count(*) from Player" ).iterate().next() ).intValue();
		assertEquals("expected count of 0 but got = " + count, count, 0);
		tx.commit();
		s.close();
	}
