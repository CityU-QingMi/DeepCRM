	@Before
	public void init() {
		doInHibernate( this::sessionFactory, session -> {
			session.doWork( connection -> {

				Statement statement = null;
				try {
					statement = connection.createStatement();
					statement.executeUpdate(
						"CREATE OR REPLACE FUNCTION fn_person ( " +
						"   personId IN NUMBER) " +
						"    RETURN SYS_REFCURSOR " +
						"IS " +
						"    persons SYS_REFCURSOR; " +
						"BEGIN " +
						"   OPEN persons FOR " +
						"        SELECT " +
						"            p.id AS \"p.id\", " +
						"            p.name AS \"p.name\", " +
						"            p.nickName AS \"p.nickName\" " +
						"       FROM person p " +
						"       WHERE p.id = personId; " +
						"   RETURN persons; " +
						"END;"
					);
				}
				catch (SQLException ignore) {
				}
				finally {
					if ( statement != null ) {
						statement.close();
					}
				}
			} );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Person person1 = new Person();
			person1.setId( 1L );
			person1.setName( "John Doe" );
			person1.setNickName( "JD" );
			session.persist( person1 );
		} );
	}
