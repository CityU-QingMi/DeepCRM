	@Test
	public void testIdClass() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Footballer fb = new Footballer("David", "Beckam", "Arsenal");
		GoalKeeper keeper = new GoalKeeper("Fabien", "Bartez", "OM");
		s.persist(fb);
		s.persist(keeper);
		tx.commit();
		s.clear();

		// lookup by id
		tx = s.beginTransaction();
		FootballerPk fpk = new FootballerPk("David", "Beckam");
		fb = (Footballer) s.get(Footballer.class, fpk);
		FootballerPk fpk2 = new FootballerPk("Fabien", "Bartez");
		keeper = (GoalKeeper) s.get(GoalKeeper.class, fpk2);
		assertNotNull(fb);
		assertNotNull(keeper);
		assertEquals("Beckam", fb.getLastname());
		assertEquals("Arsenal", fb.getClub());
		assertEquals(1, s.createQuery(
				"from Footballer f where f.firstname = 'David'").list().size());
		tx.commit();

		// reattach by merge
		tx = s.beginTransaction();
		fb.setClub("Bimbo FC");
		s.merge(fb);
		tx.commit();

		// reattach by saveOrUpdate
		tx = s.beginTransaction();
		fb.setClub("Bimbo FC SA");
		s.saveOrUpdate(fb);
		tx.commit();

		// clean up
		s.clear();
		tx = s.beginTransaction();
		fpk = new FootballerPk("David", "Beckam");
		fb = (Footballer) s.get(Footballer.class, fpk);
		assertEquals("Bimbo FC SA", fb.getClub());
		s.delete(fb);
		s.delete(keeper);
		tx.commit();
		s.close();
	}
