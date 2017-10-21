	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCollectionReplaceOnUpdate() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Category c = new Category();
		List list = new ArrayList();
		c.setSubcategories(list);
		list.add( new Category() );
		s.save(c);
		t.commit();
		s.close();
		c.setSubcategories(list);

		s = openSession();
		t = s.beginTransaction();
		s.update(c);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Category) s.load( Category.class, new Long( c.getId() ), LockMode.UPGRADE );
		List list2 = c.getSubcategories();
		t.commit();
		s.close();

		assertTrue( !Hibernate.isInitialized( c.getSubcategories() ) );

		c.setSubcategories(list2);
		s = openSession();
		t = s.beginTransaction();
		s.update(c);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Category) s.load( Category.class, new Long( c.getId() ), LockMode.UPGRADE );
		assertTrue( c.getSubcategories().size()==1 );
		s.delete(c);
		t.commit();
		s.close();
	}
