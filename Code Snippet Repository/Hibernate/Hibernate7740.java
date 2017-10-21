	@Test
	@TestForIssue( jiraKey = "")
	@FailureExpected( jiraKey = "")
	public void testAllPropertiesCopied() {
		final Order order = new Order();
		order.id = 1L;
		order.name = "order";
		// Item.id is an identity so don't initialize it.
		Item item = new Item();
		item.name = "item";
		order.items.add( item );

		addEntityListeners( order );

		Session s = openSession();
		s.getTransaction().begin();
		s.merge( order );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		s.delete( order );
		s.getTransaction().commit();
		s.close();
	}
