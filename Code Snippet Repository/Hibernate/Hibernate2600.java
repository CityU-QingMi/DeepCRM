	@Override
	public void initialize() {
		// TODO : this really needs to be delayed until after we definitively know the operand node type;
		// where this is currently a problem is parameters for which where we cannot unequivocally
		// resolve an expected type
		Type operandType = extractDataType( getOperand() );
		if ( operandType == null ) {
			return;
		}
		SessionFactoryImplementor sessionFactory = getSessionFactoryHelper().getFactory();
		int operandColumnSpan = operandType.getColumnSpan( sessionFactory );
		if ( operandColumnSpan > 1 ) {
			mutateRowValueConstructorSyntax( operandColumnSpan );
		}
	}
