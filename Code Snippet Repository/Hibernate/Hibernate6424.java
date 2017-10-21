	@Test
	public void testCreateProject() {
		Product product = new Product();
		product.setName("Product 1");

		Session session = openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();

		Order order = new Order();
		order.setName("Order 1");
		order.addLineItem(product, 2);

		session = openSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		session.close();

		Long orderId = order.getId();

		session = openSession();
		session.beginTransaction();
		order = (Order) session.get(Order.class, orderId);
		assertEquals(1, order.getLineItems().size());
		session.delete( order );
		session.getTransaction().commit();
		session.close();
	}
