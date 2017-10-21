	private void handleWithFragment(FromElement fromElement, AST hqlWithNode) throws SemanticException {
		try {
			withClause( hqlWithNode );
			AST hqlSqlWithNode = returnAST;
			if ( LOG.isDebugEnabled() ) {
				LOG.debug(
						"handleWithFragment() : " + getASTPrinter().showAsString(
								hqlSqlWithNode,
								"-- with clause --"
						)
				);
			}
			WithClauseVisitor visitor = new WithClauseVisitor( fromElement, queryTranslatorImpl );
			NodeTraverser traverser = new NodeTraverser( visitor );
			traverser.traverseDepthFirst( hqlSqlWithNode );

			SqlGenerator sql = new SqlGenerator( getSessionFactoryHelper().getFactory() );
			sql.whereExpr( hqlSqlWithNode.getFirstChild() );

			fromElement.setWithClauseFragment( "(" + sql.getSQL() + ")" );
		}
		catch (SemanticException e) {
			throw e;
		}
		catch (InvalidWithClauseException e) {
			throw e;
		}
		catch (Exception e) {
			throw new SemanticException( e.getMessage() );
		}
	}
