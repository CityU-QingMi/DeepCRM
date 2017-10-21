	@Test(expected = TransactionRequiredException.class)
	public void testCollectionUpdateOutsideActiveTransaction() {
		Session session = openSession();

		// Revision 1
		session.getTransaction().begin();
		Person person = new Person();
		Name name = new Name();
		name.setName( "Name" );
		person.getNames().add( name );
		session.saveOrUpdate( person );
		session.getTransaction().commit();

		// Illegal collection update outside of active transaction.
		person.getNames().remove( name );
		session.saveOrUpdate( person );
		session.flush();

		session.close();
	}
