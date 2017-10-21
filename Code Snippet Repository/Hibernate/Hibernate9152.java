	public void testCaseInsensitiveComparison() {
		Session s = openSession();
		s.beginTransaction();
		Item i = new Item();
		i.setName( "Widget" );
		i.setDescription( "A really top-quality, full-featured widget." );
		s.save( i );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List result = s.createQuery( queryString ).list();
		assertEquals(1, result.size());
		i = (Item) s.get( Item.class, new Long(i.getId()) );
		assertEquals( i.getName(), "Widget" );
		s.delete(i);
		s.getTransaction().commit();
		s.close();
	}
