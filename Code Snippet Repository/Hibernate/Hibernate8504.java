	@Test
	public void testListRemove() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz b = new Baz();
		List stringList = new ArrayList();
		List feeList = new ArrayList();
		b.setFees(feeList);
		b.setStringList(stringList);
		feeList.add( new Fee() );
		feeList.add( new Fee() );
		feeList.add( new Fee() );
		feeList.add( new Fee() );
		stringList.add("foo");
		stringList.add("bar");
		stringList.add("baz");
		stringList.add("glarch");
		s.save(b);
		s.flush();
		stringList.remove(1);
		feeList.remove(1);
		s.flush();
		s.evict(b);
		s.refresh(b);
		assertTrue( b.getFees().size()==3 );
		stringList = b.getStringList();
		assertTrue(
			stringList.size()==3 &&
			"baz".equals( stringList.get(1) ) &&
			"foo".equals( stringList.get(0) )
		);
		s.delete(b);
		doDelete( s, "from Fee" );
		s.getTransaction().commit();
		s.close();
	}
