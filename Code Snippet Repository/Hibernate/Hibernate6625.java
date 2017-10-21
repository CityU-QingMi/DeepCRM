	@Test
	public void testEnumAsId() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		PlanetCheatSheet mercury = new PlanetCheatSheet();
		mercury.setPlanet(Planet.MERCURY);
		mercury.setMass(3.303e+23);
		mercury.setRadius(2.4397e6);
		mercury.setNumberOfInhabitants(0);
		s.persist(mercury);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		PlanetCheatSheet mercuryFromDb = (PlanetCheatSheet) s.get(PlanetCheatSheet.class, mercury.getPlanet());
		assertNotNull(mercuryFromDb);
        log.debug(mercuryFromDb.toString());
		s.delete(mercuryFromDb);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		mercury = (PlanetCheatSheet) s.get(PlanetCheatSheet.class, Planet.MERCURY);
		assertNull(mercury);
		tx.commit();
		s.close();
	}
