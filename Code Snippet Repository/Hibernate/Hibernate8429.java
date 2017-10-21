	private DestinationEntity createDestination(FromEntity fromEntity, String fullName) {
		final DestinationEntity destinationEntity = new DestinationEntity( fromEntity, fullName );

		Session session = openSession();
		session.getTransaction().begin();
		session.save( destinationEntity );
		session.getTransaction().commit();
		session.close();
		return destinationEntity;
	}
