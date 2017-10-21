	@Test
	@TestForIssue( jiraKey = "" )
	public void testSetCurrentSessionOverwritesNonConnectedSesssionFlushed() {
		Parent p = new Parent();
		Child c = new Child();
		p.children.add( c );

		Session s1 = openSession();
		s1.getTransaction().begin();
		s1.saveOrUpdate( p );

		// flush the session so that p.children will contain its role
		s1.flush();

		// Now remove the collection from the PersistenceContext without unsetting its session
		// This should never be done in practice; it is done here only to test that the warning
		// gets logged. s1 will not function properly so the transaction will ultimately need
		// to be rolled-back.

		CollectionEntry ce = (CollectionEntry) ( (SessionImplementor) s1 ).getPersistenceContext()
				.getCollectionEntries()
				.remove( p.children );
		assertNotNull( ce );

		// the collection session should still be s1; the collection is no longer "connected" because its
		// CollectionEntry has been removed.
		assertSame( s1, ( (AbstractPersistentCollection) p.children ).getSession() );

		Session s2 = openSession();
		s2.getTransaction().begin();

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000470:" );
		assertFalse( triggerable.wasTriggered() );

		// The following should trigger warning because we're setting a new session when the collection already
		// has a non-null session (and the collection is not "connected" to that session);
		// The collection role and key should be included in the message (no way to test that other than inspection).
		s2.saveOrUpdate( p );

		assertTrue( triggerable.wasTriggered() );

		// collection's session should be overwritten with s2
		assertSame( s2, ( (AbstractPersistentCollection) p.children ).getSession() );

		s2.getTransaction().rollback();
		s2.close();

		s1.getTransaction().rollback();
		s1.close();
	}
