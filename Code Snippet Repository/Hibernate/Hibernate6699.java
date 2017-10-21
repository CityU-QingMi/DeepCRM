	@Test
	public void testMapKeyAndIdClass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Painter picasso = new Painter();
		Painting laVie = new Painting( "La Vie", "Picasso", 50, 20 );
		picasso.getPaintings().put( "La Vie", laVie );
		Painting famille = new Painting( "La Famille du Saltimbanque", "Picasso", 50, 20 );
		picasso.getPaintings().put( "La Famille du Saltimbanque", famille );
		s.persist( picasso );

		s.flush();
		s.clear();

		picasso = (Painter) s.get( Painter.class, picasso.getId() );
		Painting painting = picasso.getPaintings().get( famille.getName() );
		assertNotNull( painting );
		assertEquals( painting.getName(), famille.getName() );
		s.delete( picasso );
		tx.rollback();
		s.close();
	}
