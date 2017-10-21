	@Test
	public void testProjectedComponent() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Student gaith = new Student();
		gaith.setName("Gaith Bell");
		gaith.setStudentNumber(123);
		gaith.setCityState( new CityState( "Chicago", "Illinois" ) );
		s.save( gaith );
		s.flush();

		List cityStates = s.createCriteria( Student.class).setProjection( Projections.property( "cityState" ) ).list();
		t.rollback();
		s.close();
	}
