	private FromElement createCollectionJoin(JoinSequence collectionJoinSequence, String tableAlias)
			throws SemanticException {
		String text = queryableCollection.getTableName();
		AST ast = createFromElement( text );
		FromElement destination = (FromElement) ast;
		Type elementType = queryableCollection.getElementType();
		if ( elementType.isCollectionType() ) {
			throw new SemanticException( "Collections of collections are not supported!" );
		}
		destination.initializeCollection( fromClause, classAlias, tableAlias );
		destination.setType( JOIN_FRAGMENT );        // Tag this node as a JOIN.
		destination.setIncludeSubclasses( false );    // Don't include subclasses in the join.
		destination.setCollectionJoin( true );        // This is a clollection join.
		destination.setJoinSequence( collectionJoinSequence );
		destination.setOrigin( origin, false );
		destination.setCollectionTableAlias( tableAlias );
//		origin.addDestination( destination );
// This was the cause of HHH-242
//		origin.setType( FROM_FRAGMENT );			// Set the parent node type so that the AST is properly formed.
		origin.setText( "" );                        // The destination node will have all the FROM text.
		origin.setCollectionJoin( true );            // The parent node is a collection join too (voodoo - see JoinProcessor)
		fromClause.addCollectionJoinFromElementByPath( path, destination );
		fromClause.getWalker().addQuerySpaces( queryableCollection.getCollectionSpaces() );
		return destination;
	}
