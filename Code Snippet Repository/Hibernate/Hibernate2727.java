	@Override
	public void initialize(AST t) {
		super.initialize( t );
		if ( t instanceof Node ) {
			Node n = (Node)t;
			filename = n.filename;
			line = n.line;
			column = n.column;
			textLength = n.textLength;
		}
	}
