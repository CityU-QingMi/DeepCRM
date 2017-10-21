	@SuppressWarnings( {""})
	@Test
	public void testQueryMapCompositeElement() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Part top = new Part("top", "The top part");
		Part bottom = new Part("bottom", "The bottom part");
		Product prod = new Product("Some Thing");
		prod.getParts().put("Top", top);
		prod.getParts().put("Bottom", bottom);
		s.persist(prod);
		
		Item item = new Item("123456", prod);
		s.persist(item);

		List list = s.createQuery("select new Part( part.name, part.description ) from Product prod join prod.parts part order by part.name desc").list();
		assertEquals( list.size(), 2 );
		assertTrue( list.get(0) instanceof Part );
		assertTrue( list.get(1) instanceof Part );
		Part part = (Part) list.get(0);
		assertEquals( part.getName(), "top" );
		assertEquals( part.getDescription(), "The top part" );
		
		list = s.createQuery("select new Part( part.name, part.description ) from Product prod join prod.parts part where index(part) = 'Top'").list();
		assertEquals( list.size(), 1 );
		assertTrue( list.get(0) instanceof Part );
		part = (Part) list.get(0);
		assertEquals( part.getName(), "top" );
		assertEquals( part.getDescription(), "The top part" );
		
		list = s.createQuery("from Product p where 'Top' in indices(p.parts)").list();
		assertEquals( list.size(), 1 );
		assertSame( list.get(0), prod );
		
		list = s.createQuery("select i from Item i join i.product p where 'Top' in indices(p.parts)").list();
		assertEquals( list.size(), 1 );
		assertSame( list.get(0), item );
		
		list = s.createQuery("from Item i where 'Top' in indices(i.product.parts)").list();
		assertEquals( list.size(), 1 );
		assertSame( list.get(0), item );
		
		s.delete(item);
		s.delete(prod);
		t.commit();
		s.close();
	}
