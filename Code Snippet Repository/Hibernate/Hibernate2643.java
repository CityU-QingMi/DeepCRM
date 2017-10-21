	public void finishInit() {
		if ( entityJoinFromElements == null ) {
			return;
		}

		for ( EntityJoinFromElement entityJoinFromElement : entityJoinFromElements ) {
			ASTUtil.appendChild( this, entityJoinFromElement );
		}

		entityJoinFromElements.clear();
	}
