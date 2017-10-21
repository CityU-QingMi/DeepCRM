	public void processNumeric(AST literal) {
		if ( literal.getType() == NUM_INT
				|| literal.getType() == NUM_LONG
				|| literal.getType() == NUM_BIG_INTEGER ) {
			literal.setText( determineIntegerRepresentation( literal.getText(), literal.getType() ) );
		}
		else if ( literal.getType() == NUM_FLOAT
				|| literal.getType() == NUM_DOUBLE
				|| literal.getType() == NUM_BIG_DECIMAL ) {
			literal.setText( determineDecimalRepresentation( literal.getText(), literal.getType() ) );
		}
		else {
			LOG.unexpectedLiteralTokenType( literal.getType() );
		}
	}
