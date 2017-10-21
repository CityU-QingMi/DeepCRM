	@SuppressWarnings( {""})
	private void cleanup() {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "delete from NumberedNode where parent is not null" ).executeUpdate();
		s.createQuery( "delete from NumberedNode" ).executeUpdate();

		s.createQuery( "delete from Node where parent is not null" ).executeUpdate();
		s.createQuery( "delete from Node" ).executeUpdate();

		s.createQuery( "delete from VersionedEntity where parent is not null" ).executeUpdate();
		s.createQuery( "delete from VersionedEntity" ).executeUpdate();
		s.createQuery( "delete from TimestampedEntity" ).executeUpdate();

		s.createQuery( "delete from Competitor" ).executeUpdate();
		s.createQuery( "delete from Competition" ).executeUpdate();

		for ( Employer employer : (List<Employer>) s.createQuery( "from Employer" ).list() ) {
			s.delete( employer );
		}

		s.getTransaction().commit();
		s.close();
	}
