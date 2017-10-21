	@Test
	@Priority(10)
	public void initData() {

		initializeSession();

		Person pers1 = new Person( "Hernan", 28 );
		Person pers2 = new Person( "Leandro", 29 );
		Person pers3 = new Person( "Barba", 30 );

		//REV 1
		getSession().getTransaction().begin();
		getSession().persist( "Personaje", pers1 );
		id_pers1 = pers1.getId();
		getSession().getTransaction().commit();

		//REV 2
		getSession().getTransaction().begin();
		pers1 = (Person) getSession().get( "Personaje", id_pers1 );
		pers1.setAge( 29 );
		getSession().persist( "Personaje", pers1 );
		getSession().persist( "Personaje", pers2 );
		id_pers2 = pers2.getId();
		getSession().getTransaction().commit();

		//REV
		getSession().getTransaction().begin();
		pers1 = (Person) getSession().get( "Personaje", id_pers1 );
		pers1.setName( "Hernan David" );
		pers2 = (Person) getSession().get( "Personaje", id_pers2 );
		pers2.setAge( 30 );
		getSession().persist( "Personaje", pers1 );
		getSession().persist( "Personaje", pers2 );
		getSession().persist( "Personaje", pers3 );
		id_pers3 = pers3.getId();
		getSession().getTransaction().commit();

		getSession().getTransaction().begin();
		currentPers1 = (Person) getSession().get( "Personaje", id_pers1 );
		getSession().getTransaction().commit();

	}
