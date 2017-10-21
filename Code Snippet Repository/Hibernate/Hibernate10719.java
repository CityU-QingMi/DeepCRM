	@Test
	@Priority(10)
	public void initData() {
		// Revision 1 - Create indexed entries.
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Parent p = new Parent( 1 );
			p.addChild( new Child( 1, "child1" ) );
			p.addChild( new Child( 2, "child2" ) );
			entityManager.persist( p );
			p.getChildren().forEach( entityManager::persist );
		} );

		// Revision 2 - remove an indexed entry, resetting positions.
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Parent p = entityManager.find( Parent.class, 1 );
			// should remove child with id 1
			p.removeChild( p.getChildren().get( 0 ) );
			entityManager.merge( p );
		} );

		// Revision 3 - add new indexed entity to reset positions
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Parent p = entityManager.find( Parent.class, 1 );
			// add child with id 3
			final Child child = new Child( 3, "child3" );
			p.getChildren().add( 0, child );
			child.getParents().add( p );
			entityManager.persist( child );
			entityManager.merge( p );
		} );

		// Revision 4 - remove all children
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Parent p = entityManager.find( Parent.class, 1 );
			while ( !p.getChildren().isEmpty() ) {
				Child child = p.getChildren().get( 0 );
				p.removeChild( child );
				entityManager.remove( child );
			}
			entityManager.merge( p );
		} );
	}
