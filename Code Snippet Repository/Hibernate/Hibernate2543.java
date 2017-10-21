	@Override
	protected void registerTreat(AST pathToTreat, AST treatAs) {
		final String path = toPathText( pathToTreat );
		final String subclassName = toPathText( treatAs );
		LOG.debugf( "Registering discovered request to treat(%s as %s)", path, subclassName );

		if ( treatMap == null ) {
			treatMap = new HashMap<String, Set<String>>();
		}

		Set<String> subclassNames = treatMap.get( path );
		if ( subclassNames == null ) {
			subclassNames = new HashSet<String>();
			treatMap.put( path, subclassNames );
		}
		subclassNames.add( subclassName );
	}
