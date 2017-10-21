	@Override
	protected AST generateNamedParameter(AST delimiterNode, AST nameNode) throws SemanticException {
		String name = nameNode.getText();
		trackNamedParameterPositions( name );

		// create the node initially with the param name so that it shows
		// appropriately in the "original text" attribute
		ParameterNode parameter = (ParameterNode) astFactory.create( NAMED_PARAM, name );
		parameter.setText( "?" );

		NamedParameterSpecification paramSpec = new NamedParameterSpecification(
				delimiterNode.getLine(),
				delimiterNode.getColumn(),
				name
		);
		parameter.setHqlParameterSpecification( paramSpec );
		parameters.add( paramSpec );
		return parameter;
	}
