	@Test
	public void testCriteriaCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Fum fum = new Fum( fumKey("fum") );
		fum.setFum("a value");
		fum.getMapComponent().getFummap().put("self", fum);
		fum.getMapComponent().getStringmap().put("string", "a staring");
		fum.getMapComponent().getStringmap().put("string2", "a notha staring");
		fum.getMapComponent().setCount(1);
		s.save(fum);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Fum b = (Fum) s.createCriteria(Fum.class).add(
			Restrictions.in("fum", new String[] { "a value", "no value" } )
		)
		.uniqueResult();
		assertTrue( Hibernate.isInitialized( b.getMapComponent().getStringmap() ) );
		assertTrue( b.getMapComponent().getFummap().size()==1 );
		assertTrue( b.getMapComponent().getStringmap().size()==2 );
		s.delete(b);
		s.getTransaction().commit();
		s.close();
	}
