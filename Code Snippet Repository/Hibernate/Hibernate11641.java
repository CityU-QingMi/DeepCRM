	private void deleteCitizenWithCriteria(SessionFactory sf) throws Exception {
		withTxSession(sf, s -> {
			State france = getState(s, "Ile de France");
			Criteria criteria = s.createCriteria( Citizen.class );
			criteria.add( Restrictions.naturalId().set( "ssn", "1234" ).set( "state", france ) );
			criteria.setCacheable( true );
			Citizen c = (Citizen) criteria.uniqueResult();
			s.delete(c);
		});
	}
