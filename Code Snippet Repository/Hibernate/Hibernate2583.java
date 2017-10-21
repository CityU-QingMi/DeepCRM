	private void initializeSqlNode(AST t) {
		// Initialize SQL nodes here.
		if ( t instanceof InitializeableNode ) {
			InitializeableNode initializeableNode = (InitializeableNode) t;
			initializeableNode.initialize( walker );
		}
		if ( t instanceof SessionFactoryAwareNode ) {
			( (SessionFactoryAwareNode) t ).setSessionFactory( walker.getSessionFactoryHelper().getFactory() );
		}
	}
