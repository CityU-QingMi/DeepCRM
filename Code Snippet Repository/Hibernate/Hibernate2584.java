	protected AST create(Class c) {
		AST t;
		try {
			t = (AST) c.newInstance();
			initializeSqlNode( t );
		}
		catch (Exception e) {
			error( "Can't create AST Node " + c.getName() );
			return null;
		}
		return t;
	}
