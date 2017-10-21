	@Test
	@TestForIssue( jiraKey = "" )
	public void testSaveOrUpdateOwnerWithCollectionInNewSessionAfterFlush() {
		Parent p = new Parent();
		Child c = new Child();
		p.children.add( c );

		Session s1 = openSession();
		s1.getTransaction().begin();
		s1.saveOrUpdate( p );
		s1.flush();

		// try to save the same entity in a new session after flushing the first session

		Session s2 = openSession();
		s2.getTransaction().begin();
		try {
			s2.saveOrUpdate( p );
			s2.getTransaction().commit();
			fail( "should have thrown HibernateException" );
		}
		catch (HibernateException ex) {
			log.error( ex );
			s2.getTransaction().rollback();
		}
		finally {
			s2.close();
		}

		// should still be able to commit in first session
		s1.getTransaction().commit();
		s1.close();

		s1 = openSession();
		s1.getTransaction().begin();
		Parent pGet = s1.get( Parent.class, p.id );
		assertEquals( c.id, pGet.children.iterator().next().id );
		session.delete( pGet );
		s1.getTransaction().commit();
		session.close();
	}
