	@Override
	protected void beginFunctionTemplate(AST node, AST nameNode) {
		// NOTE for AGGREGATE both nodes are the same; for METHOD the first is the METHOD, the second is the
		// 		METHOD_NAME
		FunctionNode functionNode = (FunctionNode) node;
		SQLFunction sqlFunction = functionNode.getSQLFunction();
		if ( sqlFunction == null ) {
			// if SQLFunction is null we just write the function out as it appears in the hql statement
			super.beginFunctionTemplate( node, nameNode );
		}
		else {
			// this function has a registered SQLFunction -> redirect output and catch the arguments
			outputStack.addFirst( writer );
			if ( node.getType() == CAST ) {
				writer = new CastFunctionArguments();
			}
			else {
				writer = new StandardFunctionArguments();
			}
		}
	}
