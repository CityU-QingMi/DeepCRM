	@SuppressWarnings("")
	protected AST createUsingCtor(Token token, String className) {
		Class c;
		AST t;
		try {
			c = Class.forName( className );
			Class[] tokenArgType = new Class[] {antlr.Token.class};
			Constructor ctor = c.getConstructor( tokenArgType );
			if ( ctor != null ) {
				t = (AST) ctor.newInstance( token );
				initializeSqlNode( t );
			}
			else {
				// just do the regular thing if you can't find the ctor
				// Your AST must have default ctor to use this.
				t = create( c );
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException( "Invalid class or can't make instance, " + className );
		}
		return t;
	}
