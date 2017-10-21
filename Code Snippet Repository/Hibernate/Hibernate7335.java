	@Test
	@TestForIssue( jiraKey = "" )
	public void testDeleteCopyToNewOwnerInNewSessionBeforeFlush() {
		Parent p1 = new Parent();
		p1.nickNames.add( "nick" );
		Parent p2 = new Parent();
		Session s1 = openSession();
		s1.getTransaction().begin();
		s1.save( p1 );
		s1.save( p2 );
		s1.getTransaction().commit();
		s1.close();

		s1 = openSession();
		s1.getTransaction().begin();
		s1.delete( p1 );

		// Assign the deleted collection to a different entity with same collection role (p2.nickNames)
		// before committing delete.

		p2.nickNames = p1.nickNames;
		Session s2 = openSession();
		s2.getTransaction().begin();
		try {
			s2.saveOrUpdate( p2 );
			fail( "should have thrown HibernateException" );
		}
		catch (HibernateException ex) {
			log.error( ex );
			s2.getTransaction().rollback();
		}
		finally {
			s2.close();
		}

		// should still be able to commit the original delete
		s1.getTransaction().commit();
		s1.close();
	}
