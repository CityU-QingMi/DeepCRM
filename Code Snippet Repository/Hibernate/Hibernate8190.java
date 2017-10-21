	@Test
	@TestForIssue( jiraKey = "" )
	public void testHqlDeleteOnJoinedSubclass() {
		Session s = openSession();
		s.beginTransaction();
		// syntax checking on the database...
		s.createQuery( "delete from Employee" ).executeUpdate();
		s.createQuery( "delete from Person" ).executeUpdate();
		s.createQuery( "delete from Employee e" ).executeUpdate();
		s.createQuery( "delete from Person p" ).executeUpdate();
		s.createQuery( "delete from Employee where name like 'S%'" ).executeUpdate();
		s.createQuery( "delete from Employee e where e.name like 'S%'" ).executeUpdate();
		s.createQuery( "delete from Person where name like 'S%'" ).executeUpdate();
		s.createQuery( "delete from Person p where p.name like 'S%'" ).executeUpdate();

		// now the forms that actually fail from problem underlying HHH-1657
		// which is limited to references to properties mapped to column names existing in both tables
		// which is normally just the pks.  super critical ;)

		s.createQuery( "delete from Employee where id = 1" ).executeUpdate();
		s.createQuery( "delete from Employee e where e.id = 1" ).executeUpdate();
		s.createQuery( "delete from Person where id = 1" ).executeUpdate();
		s.createQuery( "delete from Person p where p.id = 1" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
