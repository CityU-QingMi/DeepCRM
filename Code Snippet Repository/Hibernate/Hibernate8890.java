	@Test
	@TestForIssue( jiraKey = "" )
	public void testNaturalIdChangeAfterResolveEntityFrom2LCache() {
			Session session = openSession();
			session.beginTransaction();
			AllCached it = new AllCached( "it" );
			
			session.save( it );
			Serializable id = it.getId();
			session.getTransaction().commit();
			session.close();

			session = openSession();
			session.beginTransaction();
			it = session.byId( AllCached.class ).load( id );

			it.setName( "it2" );
			it = (AllCached) session.bySimpleNaturalId( AllCached.class ).load( "it" );
			assertNull( it );
			it = (AllCached) session.bySimpleNaturalId( AllCached.class ).load( "it2" );
			assertNotNull( it );
			session.delete( it );
			session.getTransaction().commit();
			session.close();
	}
