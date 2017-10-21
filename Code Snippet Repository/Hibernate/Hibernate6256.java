	@Test
	public void testColumnUnique() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Sky sky = new Sky();
		sky.id = Long.valueOf( 2 );
		sky.color = "blue";
		sky.day = "monday";
		sky.month = "January";

		Sky sameSky = new Sky();
		sameSky.id = Long.valueOf( 3 );
		sameSky.color = "blue";
		sky.day = "tuesday";
		sky.month = "January";

		try {
			s.save( sky );
			s.flush();
			s.save( sameSky );
			tx.commit();
			fail( "unique constraints not respected" );
		}
		catch (HibernateException e) {
			//success
		}
		finally {
			if ( tx != null ) {
				tx.rollback();
			}
			s.close();
		}
	}
