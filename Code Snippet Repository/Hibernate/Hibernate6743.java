	@Test
	public void testOrderColumnInNormalBiDirectonalModel() {
		Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( Order.class )
				.addAnnotatedClass( LineItem.class )
				.buildMetadata();

		Collection lineItemsBinding = metadata.getCollectionBindings().iterator().next();

		// make sure it was interpreted as a List (aka, as having an OrderColumn at all)
		assertThat( lineItemsBinding, instanceOf( org.hibernate.mapping.List.class ) );
		org.hibernate.mapping.List asList = (org.hibernate.mapping.List) lineItemsBinding;

		// assert the OrderColumn details
		final Column positionColumn = (Column) asList.getIndex().getColumnIterator().next();
		assertThat( positionColumn.getName(), equalTo( "position" ) );

		// make sure the OrderColumn is part of the collection table
		assertTrue( asList.getCollectionTable().containsColumn( positionColumn ) );

		class TargetImpl extends GenerationTargetToStdout {
			boolean found = false;
			@Override
			public void accept(String action) {
				super.accept( action );
				if ( action.matches( "^create( (column|row))? table t_line_item.+" ) ) {
					if ( action.contains( "position" ) ) {
						found = true;
					}
				}
			}
		}

		TargetImpl target = new TargetImpl();

		new SchemaCreatorImpl( ssr ).doCreation(
				metadata,
				true,
				target
		);

		assertTrue( target.found );
	}
