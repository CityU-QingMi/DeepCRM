	@Test
	@TestForIssue( jiraKey = "" )
	public void testHqlUpdateOnJoinedSubclass() {
		Session s = openSession();
		s.beginTransaction();
		// syntax checking on the database...
		s.createQuery( "update Employee set name = 'Some Other Name' where employeeNumber like 'A%'" ).executeUpdate();
		s.createQuery( "update Employee e set e.name = 'Some Other Name' where e.employeeNumber like 'A%'" ).executeUpdate();
		s.createQuery( "update Person set name = 'Some Other Name' where name like 'S%'" ).executeUpdate();
		s.createQuery( "update Person p set p.name = 'Some Other Name' where p.name like 'S%'" ).executeUpdate();

		// now the forms that actually fail from problem underlying HHH-1657
		// which is limited to references to properties mapped to column names existing in both tables
		// which is normally just the pks.  super critical ;)

		s.createQuery( "update Employee set name = 'Some Other Name' where id = 1" ).executeUpdate();
		s.createQuery( "update Employee e set e.name = 'Some Other Name' where e.id = 1" ).executeUpdate();
		s.createQuery( "update Person set name = 'Some Other Name' where id = 1" ).executeUpdate();
		s.createQuery( "update Person p set p.name = 'Some Other Name' where p.id = 1" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}
