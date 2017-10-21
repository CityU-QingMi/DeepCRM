	@Test
	public void testDeleteParentWithBidirOrphanDeleteCollectionBasedOnPropertyRef() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();
		User user = new User( "test" );
		user.addMail( "test" );
		user.addMail( "test" );
		session.save( user );
		txn.commit();
		session.close();

		session = openSession();
		txn = session.beginTransaction();
		user = ( User ) session.load( User.class, user.getId() );
		session.delete( user );
		txn.commit();
		session.close();

		session = openSession();
		txn = session.beginTransaction();
		session.createQuery( "delete from Mail where alias = :alias" ).setString( "alias", "test" ).executeUpdate();
		session.createQuery( "delete from User where userid = :userid" ).setString( "userid", "test" ).executeUpdate();
		txn.commit();
		session.close();
	}
