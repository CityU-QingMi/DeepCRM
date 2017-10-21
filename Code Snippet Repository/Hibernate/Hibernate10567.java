	@Test
	@Priority(10)
	public void initData() {
		initializeSession();

		//REV 1
		getSession().getTransaction().begin();
		Person owner = new Person( "Lukasz", 25 );
		Person driver = new Person( "Kinga", 24 );
		Car car = new Car( 1, owner, driver );
		getSession().persist( "Personaje", owner );
		getSession().persist( "Driveraje", driver );
		getSession().persist( car );
		getSession().getTransaction().commit();

		carId = car.getId();
		ownerId = owner.getId();
		driverId = driver.getId();
	}
