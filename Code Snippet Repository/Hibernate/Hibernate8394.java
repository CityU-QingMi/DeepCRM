	@Test
	public void basicUsageTest() {
		Session session = openSession();
		session.beginTransaction();
		session.save( new Cat( 1 ) );
		session.save( new Dog( 2 ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.createQuery( "from Animal" ).list();
		Cat cat = (Cat) session.get( Cat.class, 1 );
		assertNotNull( cat );
		session.delete( cat );
		Dog dog = (Dog) session.get( Dog.class, 2 );
		assertNotNull( dog );
		session.delete( dog );
		session.getTransaction().commit();
		session.close();
	}
