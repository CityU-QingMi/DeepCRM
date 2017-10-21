	@Override
	protected void afterMetadataBuilt(Metadata metadata) {
		Collection children = metadata.getCollectionBinding( Parent.class.getName() + ".children" );
		Component childComponents = ( Component ) children.getElement();
		Formula f = ( Formula ) childComponents.getProperty( "bioLength" ).getValue().getColumnIterator().next();

		SQLFunction lengthFunction = metadata.getDatabase().getJdbcEnvironment().getDialect().getFunctions().get( "length" );
		if ( lengthFunction != null ) {
			ArrayList args = new ArrayList();
			args.add( "bio" );
			f.setFormula( lengthFunction.render( StandardBasicTypes.INTEGER, args, null ) );
		}
	}
