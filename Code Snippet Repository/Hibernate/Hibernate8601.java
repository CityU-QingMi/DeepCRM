	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCollectionReplace2() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Category c = new Category();
		List list = new ArrayList();
		c.setSubcategories(list);
		list.add( new Category() );
		Category c2 = new Category();
		s.save(c2);
		s.save(c);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Category) s.load( Category.class, new Long( c.getId() ), LockMode.UPGRADE );
		List list2 = c.getSubcategories();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c2 = (Category) s.load( Category.class, new Long( c2.getId() ), LockMode.UPGRADE );
		c2.setSubcategories(list2);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c2 = (Category) s.load( Category.class, new Long( c2.getId() ), LockMode.UPGRADE );
		assertTrue( c2.getSubcategories().size()==1 );
		s.delete(c2);
		s.delete( s.load( Category.class, new Long( c.getId() ) ) );
		t.commit();
		s.close();
	}
