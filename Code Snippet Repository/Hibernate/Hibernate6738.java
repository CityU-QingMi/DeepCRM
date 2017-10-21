	@Test
	public void testUniqueConstaintOnSecondaryTable() throws Exception {
		Cat cat = new Cat();
		cat.setStoryPart2( "My long story" );
		Cat cat2 = new Cat();
		cat2.setStoryPart2( "My long story" );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.persist( cat );
			s.persist( cat2 );
			tx.commit();
			fail( "unique constraints violation on secondary table" );
		}
		catch (PersistenceException e) {
			try {
				assertTyping( ConstraintViolationException.class, e.getCause() );
				//success
			}
			finally {
				tx.rollback();
			}
		}
		finally {
			s.close();
		}
	}
