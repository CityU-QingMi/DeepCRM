	public String render(Type firstArgumentType, final List args, final SessionFactoryImplementor factory) {
		final StringBuffer buf = new StringBuffer();
		if ( args.isEmpty() ) {
			throw new IllegalArgumentException(
					"First Argument in arglist must be object to which"
							+ " method is applied"
			);
		}

		buf.append( "CASE " ).append( args.get( 0 ) ).append( "." ).append(
				getName()
		).append( "()" );
		buf.append( " WHEN 1 THEN 'POINT'" ).append(
				" WHEN 2 THEN 'LINESTRING'"
		).append(
				" WHEN 3 THEN 'POLYGON'"
		).append(
				" WHEN 5 THEN 'MULTIPOINT'"
		).append(
				" WHEN 6 THEN 'MULTILINE'"
		).append(
				" WHEN 7 THEN 'MULTIPOLYGON'"
		).append( " END" );
		return buf.toString();
	}
