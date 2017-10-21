	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCollectionRefresh() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Category c = new Category();
		List list = new ArrayList();
		c.setSubcategories(list);
		list.add( new Category() );
		c.setName("root");
		Serializable id = s.save(c);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		c = (Category) s.load(Category.class, id);
		s.refresh( c );
		s.flush();
		assertTrue( c.getSubcategories().size() == 1 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		c = (Category) s.load(Category.class, id);
		assertTrue( c.getSubcategories().size() == 1 );
		s.delete( c );
		s.getTransaction().commit();
		s.close();
	}
