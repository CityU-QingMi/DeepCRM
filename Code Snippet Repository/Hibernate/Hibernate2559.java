	@Override
	protected AST generatePositionalParameter(AST inputNode) throws SemanticException {
		if ( namedParameters.size() > 0 ) {
			throw new SemanticException(
					"cannot define positional parameter after any named parameters have been defined"
			);
		}
		LOG.warnf(
				"[DEPRECATION] Encountered positional parameter near line %s, column %s in HQL: [%s].  Positional parameter " +
						"are considered deprecated; use named parameters or JPA-style positional parameters instead.",
				inputNode.getLine(),
				inputNode.getColumn(),
				queryTranslatorImpl.getQueryString()
		);
		ParameterNode parameter = (ParameterNode) astFactory.create( PARAM, "?" );
		PositionalParameterSpecification paramSpec = new PositionalParameterSpecification(
				inputNode.getLine(),
				inputNode.getColumn(),
				positionalParameterCount++
		);
		parameter.setHqlParameterSpecification( paramSpec );
		parameters.add( paramSpec );
		return parameter;
	}
