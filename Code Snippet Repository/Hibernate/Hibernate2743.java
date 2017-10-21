	@Override
	public void setExpectedType(Type expectedType) {
		AST option = getFirstChild();
		while ( option != null ) {
			if ( option.getType() == HqlSqlTokenTypes.WHEN ) {
				if ( ParameterNode.class.isAssignableFrom( option.getFirstChild().getNextSibling().getClass() ) ) {
					((ParameterNode) option.getFirstChild().getNextSibling()).setExpectedType( expectedType );
				}
			}
			else if ( option.getType() == HqlSqlTokenTypes.ELSE ) {
				if ( ParameterNode.class.isAssignableFrom( option.getFirstChild().getClass() ) ) {
					((ParameterNode) option.getFirstChild()).setExpectedType( expectedType );
				}
			}
			option = option.getNextSibling();
		}
	}
