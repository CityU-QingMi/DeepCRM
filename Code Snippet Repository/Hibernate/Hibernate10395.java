	@Test(expected = TransactionRequiredException.class)
	public void testCollectionRemovalOutsideActiveTransaction() {
		Session session = openSession();

		// Revision 1
		session.getTransaction().begin();
		Person person = new Person();
		Name name = new Name();
		name.setName( "Name" );
		person.getNames().add( name );
		session.saveOrUpdate( person );
		session.getTransaction().commit();

		// Illegal collection removal outside of active transaction.
		person.setNames( null );
		session.saveOrUpdate( person );
		session.flush();

		session.close();
	}
