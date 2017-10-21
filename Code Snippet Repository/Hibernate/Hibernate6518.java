	@Test
	@FailureExpected( jiraKey = "")
	public void testBasicOps() {
		Session session = openSession();
		session.beginTransaction();
		Alias alias = new Alias( "Public Enemy", "Number 1", "FBI" );
		session.persist( alias );
		Person person = new Person( "John", "Dillinger" );
		person.getName().getAliases().add( alias );
		session.persist( person );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		person = (Person) session.load( Person.class, person.getId() );
		session.delete( person );
		List aliases = session.createQuery( "from Alias" ).list();
		assertEquals( 0, aliases.size() );
		session.getTransaction().commit();
		session.close();
	}
