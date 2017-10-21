	public String render(Type firstArgumentType, final List args, final SessionFactoryImplementor factory) {
		final StringBuilder buf = new StringBuilder();
		buf.append( "MDSYS." ).append( getName() ).append( "(" );
		for ( int i = 0; i < args.size(); i++ ) {
			if ( i > 0 ) {
				buf.append( "," );
			}
			if ( geomArrays[i] ) {
				buf.append( "MDSYS.ST_GEOMETRY.FROM_SDO_GEOM(" ).append(
						args.get( i )
				).append( ")" );
			}
			else {
				buf.append( args.get( i ) );
			}

		}
		buf.append( ")" );
		return ( isGeometryTyped ) ? buf
				.append( ".geom" ).toString() : buf.toString();
	}
