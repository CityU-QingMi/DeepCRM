	@Test
	public void testReferencedColumnNameToSuperclass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		BuildingCompany comp = new BuildingCompany();
		comp.setFoundedIn( new Date() );
		comp.setName( "Builder century corp.");
		s.persist( comp );
		Building building = new Building();
		building.setCompany( comp );
		s.persist( building );
		s.flush();
		s.clear();
		building = (Building) s.get( Building.class, building.getId() );
		assertEquals( comp.getName(), building.getCompany().getName() );
		tx.rollback();
		s.close();
	}
