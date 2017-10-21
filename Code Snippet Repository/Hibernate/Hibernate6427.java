	@Test
	@TestForIssue(jiraKey = "")
	public void testMergeDetachedIdManyToOne() throws Exception {
		ShoppingCart cart = new ShoppingCart("cart1");

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( cart );
		s.getTransaction().commit();
		s.close();

		// cart is detached now
		LineItem lineItem = new LineItem( 0, "description2", cart );
		cart.addLineItem( lineItem );

		// merge lineItem with an ID with detached many-to-one
		s = openSession();
		s.getTransaction().begin();
		s.merge(lineItem);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		ShoppingCart updatedCart = s.get( ShoppingCart.class, "cart1" );
		assertEquals( 1, updatedCart.getLineItems().size() );
		assertEquals("description2", updatedCart.getLineItems().get( 0 ).getDescription());
		s.getTransaction().commit();
		s.close();
	}
