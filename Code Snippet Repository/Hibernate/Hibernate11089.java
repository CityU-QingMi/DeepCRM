	@Test
	@Priority(10)
	public void initData() {
		final EntityManager em = getEntityManager();

		// revision 1
		em.getTransaction().begin();
		address1 = new Address("address1", 1);
		em.persist(address1);
		Address address2 = new Address("address2", 2);
		em.persist(address2);
		person1 = new Person("person1", 30, address1);
		em.persist(person1);
		person2 = new Person("person2", 20, null);
		em.persist(person2);
		Person person3 = new Person("person3", 10, address1);
		em.persist(person3);
		car1 = new Car("car1");
		car1.setOwner(person1);
		em.persist(car1);
		car2 = new Car("car2");
		car2.setOwner(person2);
		em.persist(car2);
		car3 = new Car("car3");
		em.persist(car3);
		em.getTransaction().commit();

		// revision 2
		em.getTransaction().begin();
		person2.setAge(21);
		em.getTransaction().commit();
	}
