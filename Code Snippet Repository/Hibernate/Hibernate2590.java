	@Override
	protected void endFunctionTemplate(AST node) {
		FunctionNode functionNode = (FunctionNode) node;
		SQLFunction sqlFunction = functionNode.getSQLFunction();
		if ( sqlFunction == null ) {
			super.endFunctionTemplate( node );
		}
		else {
			final Type functionType = functionNode.getFirstArgumentType();
			// this function has a registered SQLFunction -> redirect output and catch the arguments
			FunctionArgumentsCollectingWriter functionArguments = (FunctionArgumentsCollectingWriter) writer;
			writer = outputStack.removeFirst();
			out( sqlFunction.render( functionType, functionArguments.getArgs(), sessionFactory ) );
		}
	}
