	@Test
	@TestForIssue( jiraKey = "" )
	public void testDeleteCommitCopyToNewOwnerNewCollectionRoleInNewSession() {
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
		s1.flush();
		s1.getTransaction().commit();

		// need to commit after flushing; otherwise, will get lock failure when try to move the collection below

		assertNull( ( (SessionImplementor) s1 ).getPersistenceContext().getEntry( p1 ) );
		CollectionEntry ceChildren = ( (SessionImplementor) s1 ).getPersistenceContext().getCollectionEntry( (PersistentCollection) p1.children );
		CollectionEntry ceNickNames = ( (SessionImplementor) s1 ).getPersistenceContext().getCollectionEntry( (PersistentCollection) p1.nickNames );
		assertNull( ceChildren );
		assertNull( ceNickNames );
		assertNull( ( ( AbstractPersistentCollection) p1.children ).getSession() );
		assertNull( ( ( AbstractPersistentCollection) p1.oldChildren ).getSession() );
		assertNull( ( ( AbstractPersistentCollection) p1.nickNames ).getSession() );
		assertNull( ( (AbstractPersistentCollection) p1.oldNickNames ).getSession() );

		// Assign the deleted collection to a different entity with different collection role (p2.oldNickNames)

		p2.oldNickNames = p1.nickNames;
		Session s2 = openSession();
		s2.getTransaction().begin();
		s2.saveOrUpdate( p2 );
		s2.getTransaction().commit();
		s2.close();

		s1.close();
	}
