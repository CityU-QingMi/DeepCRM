	@Test
	public void testBidirOneToOne() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		String id = "10";
		Incident i = em.find( Incident.class, id );
		if ( i == null ) {
			i = new Incident( id );
			IncidentStatus ist = new IncidentStatus( id );
			i.setIncidentStatus( ist );
			ist.setIncident( i );
			em.persist( i );
		}
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.remove( em.find(Incident.class, id) );
		em.getTransaction().commit();
		em.close();
	}
