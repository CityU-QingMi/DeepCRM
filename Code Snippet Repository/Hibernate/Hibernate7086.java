	public void testEjb3Xml() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		CarModel model = new CarModel();
		model.setYear( new Date() );
		Manufacturer manufacturer = new Manufacturer();
		//s.persist( manufacturer );
		model.setManufacturer( manufacturer );
		manufacturer.getModels().add( model );
		s.persist( model );
		s.flush();
		s.clear();

		model.setYear( new Date() );
		manufacturer = (Manufacturer) s.get( Manufacturer.class, manufacturer.getId() );
		@SuppressWarnings("unchecked")
		List<Model> cars = s.getNamedQuery( "allModelsPerManufacturer" )
				.setParameter( "manufacturer", manufacturer )
				.list();
		assertEquals( 1, cars.size() );
		for ( Model car : cars ) {
			assertNotNull( car.getManufacturer() );
			s.delete( manufacturer );
			s.delete( car );
		}
		tx.rollback();
		s.close();
	}
