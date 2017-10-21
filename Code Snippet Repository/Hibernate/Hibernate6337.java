	@Test
	public void testMerge() {
		Session s;
		Transaction tx;
		s = openSession();
		Tooth tooth = new Tooth();
		Tooth rightTooth = new Tooth();
		tooth.type = "canine";
		tooth.rightNeighbour = rightTooth;
		rightTooth.type = "incisive";
		s.persist( rightTooth );
		s.persist( tooth );
		tx = s.beginTransaction();
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		tooth = (Tooth) s.get( Tooth.class, tooth.id );
		assertEquals( "incisive", tooth.rightNeighbour.type );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		tooth.rightNeighbour.type = "premolars";
		s.merge( tooth );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		rightTooth = (Tooth) s.get( Tooth.class, rightTooth.id );
		assertEquals( "premolars", rightTooth.type );
		tx.commit();
		s.close();
	}
