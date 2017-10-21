	@Test
	public void testFormulaOnOtherSide() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Frame frame = new Frame();
		frame.setName( "Prada" );
		s.persist( frame );
		Lens l = new Lens();
		l.setFocal( 2.5f );
		l.setFrame( frame );
		s.persist( l );
		Lens r = new Lens();
		r.setFocal( 1.2f);
		r.setFrame( frame );
		s.persist( r );
		s.flush();
		s.clear();
		frame = (Frame) s.get( Frame.class, frame.getId() );
		assertEquals( 2, frame.getLenses().size() );
		assertTrue( frame.getLenses().iterator().next().getLength() <= 1/1.2f );
		assertTrue( frame.getLenses().iterator().next().getLength() >= 1/2.5f );
		tx.rollback();
		s.close();
	}
