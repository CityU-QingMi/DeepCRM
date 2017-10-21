	@Test
	public void testFieldAccess() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Sky sky = new Sky();
		sky.id = Long.valueOf( 1 );
		sky.color = "black";
		sky.area = "Paris";
		sky.day = "23";
		sky.month = "1";
		s.save( sky );
		tx.commit();
		s.close();
		sky.area = "London";

		s = openSession();
		tx = s.beginTransaction();
		sky = (Sky) s.get( Sky.class, sky.id );
		assertNotNull( sky );
		assertEquals( "black", sky.color );
		assertFalse( "Paris".equals( sky.area ) );
		tx.commit();
		s.close();
	}
