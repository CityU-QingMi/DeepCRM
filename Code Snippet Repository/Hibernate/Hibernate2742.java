	public Type getDataType() {
		final AST expression = getFirstChild();
		// option is used to hold each WHEN/ELSE in turn
		AST option = expression.getNextSibling();
		while ( option != null ) {
			final AST result;
			if ( option.getType() == HqlSqlTokenTypes.WHEN ) {
				result = option.getFirstChild().getNextSibling();
			}
			else if ( option.getType() == HqlSqlTokenTypes.ELSE ) {
				result = option.getFirstChild();
			}
			else {
				throw new QueryException(
						"Unexpected node type :" +
								ASTUtil.getTokenTypeName( HqlSqlTokenTypes.class, option.getType() ) +
								"; expecting WHEN or ELSE"
				);
			}

			if ( SqlNode.class.isInstance( result ) ) {
				final Type nodeDataType = ( (SqlNode) result ).getDataType();
				if ( nodeDataType != null ) {
					return nodeDataType;
				}
			}

			option = option.getNextSibling();
		}
		return null;
	}
