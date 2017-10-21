	@Override
	protected void prepareVersioned(AST updateNode, AST versioned) throws SemanticException {
		UpdateStatement updateStatement = (UpdateStatement) updateNode;
		FromClause fromClause = updateStatement.getFromClause();
		if ( versioned != null ) {
			// Make sure that the persister is versioned
			Queryable persister = fromClause.getFromElement().getQueryable();
			if ( !persister.isVersioned() ) {
				throw new SemanticException( "increment option specified for update of non-versioned entity" );
			}

			VersionType versionType = persister.getVersionType();
			if ( versionType instanceof UserVersionType ) {
				throw new SemanticException( "user-defined version types not supported for increment option" );
			}

			AST eq = getASTFactory().create( HqlSqlTokenTypes.EQ, "=" );
			AST versionPropertyNode = generateVersionPropertyNode( persister );

			eq.setFirstChild( versionPropertyNode );

			AST versionIncrementNode = null;
			if ( isTimestampBasedVersion( versionType ) ) {
				versionIncrementNode = getASTFactory().create( HqlSqlTokenTypes.PARAM, "?" );
				ParameterSpecification paramSpec = new VersionTypeSeedParameterSpecification( versionType );
				( (ParameterNode) versionIncrementNode ).setHqlParameterSpecification( paramSpec );
				parameters.add( 0, paramSpec );
			}
			else {
				// Not possible to simply re-use the versionPropertyNode here as it causes
				// OOM errors due to circularity :(
				versionIncrementNode = getASTFactory().create( HqlSqlTokenTypes.PLUS, "+" );
				versionIncrementNode.setFirstChild( generateVersionPropertyNode( persister ) );
				versionIncrementNode.addChild( getASTFactory().create( HqlSqlTokenTypes.IDENT, "1" ) );
			}

			eq.addChild( versionIncrementNode );

			evaluateAssignment( eq, persister, 0 );

			AST setClause = updateStatement.getSetClause();
			AST currentFirstSetElement = setClause.getFirstChild();
			setClause.setFirstChild( eq );
			eq.setNextSibling( currentFirstSetElement );
		}
	}
