	@Test
	public void testColumnDefinitionPropagation() throws Exception {
		Session s;
		s = openSession();
		s.getTransaction().begin();
		Politician casimir = new Politician();
		casimir.setName( "Casimir" );
		PoliticalParty dream = new PoliticalParty();
		dream.setName( "Dream" );
		dream.addPolitician( casimir );
		s.persist( dream );
		s.getTransaction().commit();
		s.clear();

		Transaction tx = s.beginTransaction();
		s.delete( s.get( PoliticalParty.class, dream.getName() ) );
		tx.commit();
		s.close();
	}
