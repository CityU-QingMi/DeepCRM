	@Test
	@TestForIssue( jiraKey = "")
	public void testIncompleteScroll() {
		Session s = openSession();
		s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc" ).scroll();
		results.next();
		Parent p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		// get the other parent entity from the persistence context along with its first child
		// retrieved from the resultset.
		Parent pOther = null;
		Child cOther = null;
		for ( Object entity : ( (SessionImplementor) s ).getPersistenceContext().getEntitiesByKey().values() ) {
			if ( Parent.class.isInstance( entity ) ) {
				if ( entity != p ) {
					if ( pOther != null ) {
						fail( "unexpected parent found." );
					}
					pOther = (Parent) entity;
				}
			}
			else if ( Child.class.isInstance( entity ) ) {
				if ( ! p.getChildren().contains( entity ) ) {
					if ( cOther != null ) {
						fail( "unexpected child entity found" );
					}
					cOther = (Child) entity;
				}
			}
			else {
				fail( "unexpected type of entity." );
			}
		}
		// check that the same second parent is obtained by calling Session.get()
		assertNull( pOther );
		assertNull( cOther );
		s.getTransaction().commit();
		s.close();
	}
