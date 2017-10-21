	private void showAst(ArrayList<AST> parents, PrintWriter pw, AST ast) {
		if ( ast == null ) {
			pw.println( "AST is null!" );
			return;
		}

		for ( AST parent : parents ) {
			if ( parent.getNextSibling() == null ) {

				pw.print( "   " );
			}
			else {
				pw.print( " | " );
			}
		}

		if ( ast.getNextSibling() == null ) {
			pw.print( " \\-" );
		}
		else {
			pw.print( " +-" );
		}

		showNode( pw, ast );

		ArrayList<AST> newParents = new ArrayList<AST>( parents );
		newParents.add( ast );
		for ( AST child = ast.getFirstChild(); child != null; child = child.getNextSibling() ) {
			showAst( newParents, pw, child );
		}
		newParents.clear();
	}
