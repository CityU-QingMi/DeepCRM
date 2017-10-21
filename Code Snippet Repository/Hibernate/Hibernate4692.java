		private TrimOperands(List<String> operands) {
			final int size = operands.size();
			if ( size == 1 ) {
				trimSpec = null;
				trimChar = null;
				from = null;
				trimSource = operands.get(0);
			}
			else if ( size == 4 ) {
				trimSpec = operands.get(0);
				trimChar = operands.get(1);
				from = operands.get(2);
				trimSource = operands.get(3);
			}
			else {
				if ( size < 1 || size > 4 ) {
					throw new HibernateException( "Unexpected number of trim function operands : " + size );
				}

				// trim-source will always be the last operand
				trimSource = operands.get( size - 1 );

				// ANSI SQL says that more than one operand means that the FROM is required
				if ( ! "from".equals( operands.get( size - 2 ) ) ) {
					throw new HibernateException( "Expecting FROM, found : " + operands.get( size - 2 ) );
				}
				from = operands.get( size - 2 );

				// trim-spec, if there is one will always be the first operand
				if ( "leading".equalsIgnoreCase( operands.get(0) )
						|| "trailing".equalsIgnoreCase( operands.get(0) )
						|| "both".equalsIgnoreCase( operands.get(0) ) ) {
					trimSpec = operands.get(0);
					trimChar = null;
				}
				else {
					trimSpec = null;
					if ( size - 2 == 0 ) {
						trimChar = null;
					}
					else {
						trimChar = operands.get( 0 );
					}
				}
			}
		}
