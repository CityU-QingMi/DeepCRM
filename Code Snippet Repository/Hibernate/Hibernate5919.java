	@Test
	public void testBackrefAndGenerics() throws Exception {
		final EntityType<Parent> parent = entityManagerFactory().getMetamodel().entity( Parent.class );
		assertNotNull( parent );
		final SetAttribute<? super Parent, ?> children = parent.getSet( "children" );
		assertNotNull( children );
		assertEquals( 1, parent.getPluralAttributes().size() );
		assertEquals( 4, parent.getAttributes().size() );
		final EntityType<Child> child = entityManagerFactory().getMetamodel().entity( Child.class );
		assertNotNull( child );
		assertEquals( 2, child.getAttributes().size() );
		final SingularAttribute<? super Parent, Parent.Relatives> attribute = parent.getSingularAttribute(
				"siblings", Parent.Relatives.class
		);
		final EmbeddableType<Parent.Relatives> siblings = (EmbeddableType<Parent.Relatives>) attribute.getType();
		assertNotNull(siblings);
		final SetAttribute<? super Parent.Relatives, ?> siblingsCollection = siblings.getSet( "siblings" );
		assertNotNull( siblingsCollection );
		final Type<?> collectionElement = siblingsCollection.getElementType();
		assertNotNull( collectionElement );
		assertEquals( collectionElement, child );
	}
