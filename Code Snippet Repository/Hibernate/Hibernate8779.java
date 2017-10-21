	@Test
	public void testRemoveAndAddEqualElementNonKeyModified() {
		deleteMembership( getUser(), getGroup(), getMembership() );
		Membership membershipNew = createMembership( "membership" );
		addMembership( getUser(), getGroup(), membershipNew );
		membershipNew.setName( "membership1" );

		Session s = openSession();
		s.beginTransaction();
		try {
			// The new membership is transient (it has a null surrogate ID), so
			// Hibernate assumes that it should be added to the collection.
			// Inserts are done before deletes, so a ConstraintViolationException
			// will be thrown on the insert because the unique constraint on the
			// user and group IDs in the join table is violated. See HHH-2801.
			s.merge( getUser() );
			s.getTransaction().commit();
			fail( "should have failed because inserts are before deletes");
		}
		catch (PersistenceException e) {
			s.getTransaction().rollback();
			// expected
			assertTyping( ConstraintViolationException.class, e.getCause() );
		}
		finally {
			s.close();
		}
	}
