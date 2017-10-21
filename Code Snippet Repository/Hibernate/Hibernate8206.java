	@Test
	@TestForIssue( jiraKey = "" )
	public void testIncompleteScrollLast() {
		Session s = openSession();
		s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc" ).scroll();
		results.next();
		Parent p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		results.last();
		// get the other parent entity from the persistence context.
		// since the result set was scrolled to the end, the other parent entity's collection has been
		// properly initialized.
		Parent pOther = null;
		Set childrenOther = new HashSet();
		for ( Object entity : ( ( SessionImplementor) s ).getPersistenceContext().getEntitiesByKey().values() ) {
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
					childrenOther.add( entity );
				}
			}
			else {
				fail( "unexpected type of entity." );
			}
		}
		// check that the same second parent is obtained by calling Session.get()
		assertNotNull( pOther );
		assertSame( pOther, s.get( Parent.class, pOther.getId() ) );
		// access pOther's collection; should be completely loaded
		assertTrue( Hibernate.isInitialized( pOther.getChildren() ) );
		assertEquals( childrenOther, pOther.getChildren() );
		assertResultFromOneUser( pOther );
		s.getTransaction().commit();
		s.close();
	}
