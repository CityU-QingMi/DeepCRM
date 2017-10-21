	@Test
	public void testElementCollectionConversion() {
		Session session = openSession();
		session.getTransaction().begin();
		Customer customer = new Customer();
		customer.id = 1;
		customer.set = new HashSet<Color>();
		customer.set.add(Color.RED);
		customer.set.add(Color.GREEN);
		customer.set.add(Color.BLUE);
		customer.map = new HashMap<Color, Status>();
		customer.map.put(Color.RED, Status.INACTIVE);
		customer.map.put(Color.GREEN, Status.ACTIVE);
		customer.map.put(Color.BLUE, Status.PENDING);
		session.persist(customer);
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		assertEquals(customer.set, session.get(Customer.class, 1).set);
		assertEquals(customer.map, session.get(Customer.class, 1).map);
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		customer = session.get(Customer.class, 1);
		session.delete(customer);
		session.getTransaction().commit();
		session.close();
	}
