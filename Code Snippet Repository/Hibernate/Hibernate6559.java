	public void testTrimmedEnumChar() throws SQLException {
		// use native SQL to insert, forcing whitespace to occur
		final Session s = openSession();
        final Connection connection = ((SessionImplementor)s).connection();
        final Statement statement = connection.createStatement();
        statement.execute("insert into EntityEnum (id, trimmed) values(1, '" + Trimmed.A.name() + "')");
        statement.execute("insert into EntityEnum (id, trimmed) values(2, '" + Trimmed.B.name() + "')");

        s.getTransaction().begin();

        // ensure EnumType can do #fromName with the trimming
        List<EntityEnum> resultList = s.createQuery("select e from EntityEnum e").list();
        assertEquals( resultList.size(), 2 );
        assertEquals( resultList.get(0).getTrimmed(), Trimmed.A );
        assertEquals( resultList.get(1).getTrimmed(), Trimmed.B );

        // ensure querying works
        final Query query = s.createQuery("select e from EntityEnum e where e.trimmed=?");
        query.setParameter( 0, Trimmed.A );
        resultList = query.list();
        assertEquals( resultList.size(), 1 );
        assertEquals( resultList.get(0).getTrimmed(), Trimmed.A );

		statement.execute( "delete from EntityEnum" );

        s.getTransaction().commit();
        s.close();
	}
