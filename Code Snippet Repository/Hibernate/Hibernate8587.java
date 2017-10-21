	@Test
	public void testDeleteOwner() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Qux q = new Qux();
		s.save(q);
		Fum f1 = new Fum( fumKey("f1") );
		Fum f2 = new Fum( fumKey("f2") );
		Set set = new HashSet();
		set.add(f1);
		set.add(f2);
		List list = new LinkedList();
		list.add(f1);
		list.add(f2);
		f1.setFum("f1");
		f2.setFum("f2");
		q.setFums(set);
		q.setMoreFums(list);
		s.save(f1);
		s.save(f2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load( Qux.class, q.getKey(), LockMode.UPGRADE );
		s.lock( q, LockMode.UPGRADE );
		s.delete(q);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		list = s.createQuery( "from Fum fum where not fum.fum='FRIEND'" ).list();
		assertTrue( "deleted owner", list.size()==2 );
		s.lock( list.get(0), LockMode.UPGRADE );
		s.lock( list.get(1), LockMode.UPGRADE );
		Iterator iter = list.iterator();
		while ( iter.hasNext() ) {
			s.delete( iter.next() );
		}
		s.getTransaction().commit();
		s.close();
	}
