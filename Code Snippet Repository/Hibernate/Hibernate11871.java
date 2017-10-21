	public String render(Type firstArgumentType, final List args, final SessionFactoryImplementor factory) {
		final StringBuffer buf = new StringBuffer();
		if ( args.isEmpty() ) {
			throw new IllegalArgumentException(
					"First Argument in arglist must be object to "
							+ "which method is applied"
			);
		}

		buf.append( args.get( 0 ) ).append( "." ).append(
				getName()
		).append( "()" );
		return buf.toString();
	}
