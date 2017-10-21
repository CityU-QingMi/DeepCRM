	@Test
	public void testUniqueConstraint() throws Exception {
		int id = 5;
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Sky sky = new Sky();
		sky.id = Long.valueOf( id++ );
		sky.color = "green";
		sky.day = "monday";
		sky.month = "March";

		Sky otherSky = new Sky();
		otherSky.id = Long.valueOf( id++ );
		otherSky.color = "red";
		otherSky.day = "friday";
		otherSky.month = "March";

		Sky sameSky = new Sky();
		sameSky.id = Long.valueOf( id++ );
		sameSky.color = "green";
		sameSky.day = "monday";
		sameSky.month = "March";

		s.save( sky );
		s.flush();

		s.save( otherSky );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		try {
			s.save( sameSky );
			tx.commit();
			fail( "unique constraints not respected" );
		}
		catch (PersistenceException e) {
			//success
			if ( tx != null ) {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
	}
