	@Test
	public void testProperCallbacks() {
		final MetadataImplementor metadata =
				(MetadataImplementor) new MetadataSources( serviceRegistry )
		.buildMetadata();
		final Table tbl = new Table();
		final RootClass rootClass = new RootClass( metadataBuildingContext );

		ValueVisitor vv = new ValueVisitorValidator();

		new Any( metadata, tbl ).accept( vv );
		new Array( metadata, rootClass ).accept( vv );
		new Bag( metadata, rootClass ).accept( vv );
		new Component( metadata, rootClass ).accept( vv );
		new DependantValue( metadata, tbl, null ).accept( vv );
		new IdentifierBag( metadata, rootClass ).accept( vv );
		new List( metadata, rootClass ).accept( vv );
		new ManyToOne( metadata, tbl ).accept( vv );
		new Map( metadata, rootClass ).accept( vv );
		new OneToMany( metadata, rootClass ).accept( vv );
		new OneToOne( metadata, tbl, rootClass ).accept( vv );
		new PrimitiveArray( metadata, rootClass ).accept( vv );
		new Set( metadata, rootClass ).accept( vv );
		new SimpleValue( metadata ).accept( vv );
	}
