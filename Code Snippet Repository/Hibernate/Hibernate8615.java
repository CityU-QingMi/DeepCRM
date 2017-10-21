	@Test
	public void testIncomingOutgoing() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Master master1 = new Master();
		Master master2 = new Master();
		Master master3 = new Master();
		s.save(master1);
		s.save(master2);
		s.save(master3);
		master1.addIncoming(master2);
		master2.addOutgoing(master1);
		master1.addIncoming(master3);
		master3.addOutgoing(master1);
		Serializable m1id = s.getIdentifier(master1);
		assertTrue(
				s.createFilter( master1.getIncoming(), "where this.id > 0 and this.name is not null" )
						.list()
						.size() == 2
		);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		master1 = (Master) s.load(Master.class, m1id);
		Iterator iter = master1.getIncoming().iterator();
		int i=0;
		while ( iter.hasNext() ) {
			Master m = (Master) iter.next();
			assertTrue( "outgoing", m.getOutgoing().size()==1 );
			assertTrue( "outgoing", m.getOutgoing().contains(master1) );
			s.delete(m);
			i++;
		}
		assertTrue( "incoming-outgoing", i == 2 );
		s.delete( master1 );
		s.getTransaction().commit();
		s.close();
	}
