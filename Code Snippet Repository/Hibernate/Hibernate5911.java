	@Test
	@FailureExpected( jiraKey = "" )
	public void testQuote() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			ParentData parent = new ParentData();
			entityManager.persist( parent );

			String[] childrenStr = new String[] { "One", "Two", "Three", "Four", "Five" };
			for ( String str : childrenStr ) {
				ChildData child = new ChildData( str );
				entityManager.persist( child );
				parent.getChildren().add( child );
			}

			entityManager.flush();

			List<ChildData> children = parent.getChildren();
			children.remove( 0 );
		} );
	}
