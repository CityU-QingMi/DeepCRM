	@SuppressWarnings( {""})
	@Test
	public void testMapCompositeElementWithFormula() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Part top = new Part("top", "The top part");
		Part bottom = new Part("bottom", "The bottom part");
		Product prod = new Product("Some Thing");
		prod.getParts().put("Top", top);
		prod.getParts().put("Bottom", bottom);
		s.persist(prod);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		prod = (Product) s.get(Product.class, "Some Thing");
		assertEquals( prod.getParts().size(), 2 );
		prod.getParts().remove("Bottom");
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		prod = (Product) s.get(Product.class, "Some Thing");
		assertEquals( prod.getParts().size(), 1 );
		prod.getParts().put("Top", new Part("top", "The brand new top part"));
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		prod = (Product) s.get(Product.class, "Some Thing");
		assertEquals( prod.getParts().size(), 1 );
		assertEquals(  ( (Part) prod.getParts().get("Top") ).getDescription(), "The brand new top part");
		s.delete(prod);
		t.commit();
		s.close();
	}
