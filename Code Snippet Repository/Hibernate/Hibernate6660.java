	@Test
	public void testIdClassManyToOne() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Store store = new Store();
		Customer customer = new Customer();
		s.persist( store );
		s.persist( customer );
		StoreCustomer sc = new StoreCustomer( store, customer );
		s.persist( sc );
		s.flush();
		s.clear();

		store = (Store) s.get(Store.class, store.id );
		assertEquals( 1, store.customers.size() );
		assertEquals( customer.id, store.customers.iterator().next().customer.id );
		tx.rollback();

		//TODO test Customers / ShoppingBaskets / BasketItems testIdClassManyToOneWithReferenceColumn
		s.close();
	}
