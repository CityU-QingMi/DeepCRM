	@Test
	public void testElementCollectionConversion() {
		Session session = openSession();
		session.getTransaction().begin();
		Disguise disguise = new Disguise( 1 );
		disguise.traits.add( new Traits( ColorType.BLUE, ColorType.RED ) );
		session.persist( disguise );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		assertEquals( 1, session.get( Disguise.class, 1 ).traits.size() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		disguise = session.get( Disguise.class, 1 );
		session.delete( disguise );
		session.getTransaction().commit();
		session.close();
	}
