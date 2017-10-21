	private EntityPersister resolveEntityJoinReferencedPersister(AST path) {
		if ( path.getType() == IDENT ) {
			final IdentNode pathIdentNode = (IdentNode) path;
			String name = path.getText();
			if ( name == null ) {
				name = pathIdentNode.getOriginalText();
			}
			return sessionFactoryHelper.findEntityPersisterByName( name );
		}
		else if ( path.getType() == DOT ) {
			final String pathText = ASTUtil.getPathText( path );
			return sessionFactoryHelper.findEntityPersisterByName( pathText );
		}
		return null;
	}
