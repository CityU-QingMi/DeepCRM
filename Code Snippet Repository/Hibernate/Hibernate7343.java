	@Test
	@TestForIssue( jiraKey = "" )
	public void testCopyInitializedCollectionReferenceAfterGet() {
		Parent p = new Parent();
		Child c = new Child();
		p.children.add( c );

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( p );
		s.getTransaction().commit();
		s.close();

		Session s1 = openSession();
		s1.getTransaction().begin();
		p = s1.get( Parent.class, p.id );
		Hibernate.initialize( p.children );

		// Copy p.children (initialized) into a different Parent.children and try to save in new session.

		Parent pWithSameChildren = new Parent();
		pWithSameChildren.children = p.children;

		Session s2 = openSession();
		s2.getTransaction().begin();
		try {
			s2.saveOrUpdate( pWithSameChildren );
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
