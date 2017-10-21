	@Override
	public void setText(String s) {
		// for some reason the antlr.CommonAST initialization routines force
		// this method to get called twice.  The first time with an empty string
		if ( StringHelper.isNotEmpty( s ) ) {
			constantExpression = s;
			constantValue = ReflectHelper.getConstantValue( s, factory );
			heuristicType = factory.getTypeResolver().heuristicType( constantValue.getClass().getName() );
			super.setText( s );
		}
	}
