	@Test
	public void testOneToOneCache() throws HibernateException {

		//create a new MainObject
		createMainObject();
		// load the MainObject
		readMainObject();

		//create and add Ojbect2
		addObject2();

		//here the newly created Object2 is written to the database
		//but the MainObject does not know it yet
		MainObject mainObject = readMainObject();

		assertNotNull( mainObject.getObj2() );

		// after evicting, it works.
		sessionFactory().getCache().evictEntityRegion( MainObject.class );

		mainObject = readMainObject();

		assertNotNull( mainObject.getObj2() );

	}
