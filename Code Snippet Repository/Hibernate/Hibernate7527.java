	@Test
	public void testProjectedListIncludesComponent() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Student gaith = new Student();
		gaith.setName("Gaith Bell");
		gaith.setStudentNumber(123);
		gaith.setCityState( new CityState( "Chicago", "Illinois" ) );
		s.save(gaith);
		s.flush();
		List data = s.createCriteria( Student.class)
				.setProjection( Projections.projectionList()
					.add( Projections.property( "cityState" ) )
					.add( Projections.property("name") ) )
				.list();
		t.rollback();
		s.close();
	}
