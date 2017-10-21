	@Test
	@Priority(10)
	public void initData() {

		initializeSession();

		Person pers1 = new Person( "Hernan", 28 );
		Person pers2 = new Person( "Leandro", 29 );
		Person pers3 = new Person( "Barba", 32 );
		Person pers4 = new Person( "Camomo", 15 );

		//REV 1
		getSession().getTransaction().begin();
		List<Person> owners = new ArrayList<Person>();
		owners.add( pers1 );
		owners.add( pers2 );
		owners.add( pers3 );
		Car car1 = new Car( 5, owners );

		getSession().persist( car1 );
		getSession().getTransaction().commit();
		id_pers1 = pers1.getId();
		id_car1 = car1.getId();

		owners = new ArrayList<Person>();
		owners.add( pers2 );
		owners.add( pers3 );
		owners.add( pers4 );
		Car car2 = new Car( 27, owners );
		//REV 2
		getSession().getTransaction().begin();
		Person person1 = (Person) getSession().get( "Personaje", id_pers1 );
		person1.setName( "Hernan David" );
		person1.setAge( 40 );
		getSession().persist( car1 );
		getSession().persist( car2 );
		getSession().getTransaction().commit();
		id_car2 = car2.getId();
	}
