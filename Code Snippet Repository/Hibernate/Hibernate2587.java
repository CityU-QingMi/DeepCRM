	@Override
	protected void out(AST n) {
		if ( n instanceof Node ) {
			out( ( (Node) n ).getRenderText( sessionFactory ) );
		}
		else {
			super.out( n );
		}

		if ( n instanceof ParameterNode ) {
			collectedParameters.add( ( (ParameterNode) n ).getHqlParameterSpecification() );
		}
		else if ( n instanceof ParameterContainer ) {
			if ( ( (ParameterContainer) n ).hasEmbeddedParameters() ) {
				ParameterSpecification[] specifications = ( (ParameterContainer) n ).getEmbeddedParameters();
				if ( specifications != null ) {
					collectedParameters.addAll( Arrays.asList( specifications ) );
				}
			}
		}
	}
