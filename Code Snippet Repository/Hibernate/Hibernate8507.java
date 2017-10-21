	@Test
	public void testSortables() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz b = new Baz();
		b.setName("name");
		SortedSet ss = new TreeSet();
		ss.add( new Sortable("foo") );
		ss.add( new Sortable("bar") );
		ss.add( new Sortable("baz") );
		b.setSortablez(ss);
		s.save(b);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Criteria cr = s.createCriteria(Baz.class);
		cr.setFetchMode( "topGlarchez", FetchMode.SELECT );
		List result = cr
			.addOrder( Order.asc("name") )
			.list();
		assertTrue( result.size()==1 );
		b = (Baz) result.get(0);
		assertTrue( b.getSortablez().size()==3 );
		assertEquals( ( (Sortable) b.getSortablez().iterator().next() ).getName(), "bar" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		result = s.createQuery("from Baz baz left join fetch baz.sortablez order by baz.name asc")
			.list();
		b = (Baz) result.get(0);
		assertTrue( b.getSortablez().size()==3 );
		assertEquals( ( (Sortable) b.getSortablez().iterator().next() ).getName(), "bar" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		result = s.createQuery("from Baz baz order by baz.name asc")
			.list();
		b = (Baz) result.get(0);
		assertTrue( b.getSortablez().size()==3 );
		assertEquals( ( (Sortable) b.getSortablez().iterator().next() ).getName(), "bar" );
		s.delete(b);
		s.getTransaction().commit();
		s.close();
	}
