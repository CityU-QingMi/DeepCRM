	@Test
	public void testIndexManyToOne() throws Exception {
		//TODO find a way to test indexes???
		Session s = openSession();
		s.getTransaction().begin();
		Conductor emmanuel = new Conductor();
		emmanuel.setName( "Emmanuel" );
		s.persist( emmanuel );
		Vehicule tank = new Vehicule();
		tank.setCurrentConductor( emmanuel );
		tank.setRegistrationNumber( "324VX43" );
		s.persist( tank );
		s.flush();
		s.delete( tank );
		s.delete( emmanuel );
		s.getTransaction().rollback();
		s.close();
	}
