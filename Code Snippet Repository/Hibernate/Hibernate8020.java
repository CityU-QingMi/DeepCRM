	@Test
	@TestForIssue( jiraKey = "" )
	public void testManyToManyBulkDeleteMultiTable() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Human friend = new Human();
		friend.setName( new Name( "Bob", 'B', "Bobbert" ) );
		s.save( friend );
		
		Human brett = new Human();
		brett.setName( new Name( "Brett", 'E', "Meyer" ) );
		brett.setFriends( new ArrayList() );
		brett.getFriends().add( friend );
		s.save( brett );
		
		s.flush();
		
		try {
			// multitable (joined subclass)
			s.createQuery( "delete from Human" ).executeUpdate();
			assertEquals( s.createQuery( "from Human" ).list().size(), 0 );
		}
		catch (ConstraintViolationException cve) {
			fail("The join table was not cleared prior to the bulk delete.");
		}
		finally {
			t.rollback();
			s.close();
		}
	}
