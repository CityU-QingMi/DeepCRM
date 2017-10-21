	@Test
	@Priority(10)
	public void initData() {

		initializeSession();

		Person pers1 = new Person( "Hernan", 15 );
		Person pers2 = new Person( "Leandro", 19 );

		Car car1 = new Car( 1, pers1 );
		Car car2 = new Car( 2, pers2 );

		//REV 1
		getSession().getTransaction().begin();
		getSession().persist( "Personaje", pers1 );
		getSession().persist( car1 );
		getSession().getTransaction().commit();
		id_car1 = car1.getId();
		id_pers1 = pers1.getId();

		//REV 2
		getSession().getTransaction().begin();
		pers1.setAge( 50 );
		getSession().persist( "Personaje", pers1 );
		getSession().persist( "Personaje", pers2 );
		getSession().persist( car2 );
		getSession().getTransaction().commit();
		id_car2 = car2.getId();
		id_pers2 = pers2.getId();

	}
