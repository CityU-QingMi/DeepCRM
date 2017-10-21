	@Test
	public void testCompositeIDCollections() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Fum fum1 = new Fum( fumKey("fum1") );
		Fum fum2 = new Fum( fumKey("fum2") );
		fum1.setFum("fee fo fi");
		fum2.setFum("fee fo fi");
		s.save(fum1);
		s.save(fum2);
		Qux q = new Qux();
		s.save(q);
		Set set = new HashSet();
		List list = new ArrayList();
		set.add(fum1); set.add(fum2);
		list.add(fum1);
		q.setFums(set);
		q.setMoreFums(list);
		fum1.setQuxArray( new Qux[] {q} );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load( Qux.class, q.getKey() );
		assertTrue( "collection of fums", q.getFums().size()==2 );
		assertTrue( "collection of fums", q.getMoreFums().size()==1 );
		assertTrue( "unkeyed composite id collection", ( (Fum) q.getMoreFums().get(0) ).getQuxArray()[0]==q );
		Iterator iter = q.getFums().iterator();
		iter.hasNext();
		Fum f = (Fum) iter.next();
		s.delete(f);
		iter.hasNext();
		f = (Fum) iter.next();
		s.delete(f);
		s.delete(q);
		s.getTransaction().commit();
		s.close();
	}
