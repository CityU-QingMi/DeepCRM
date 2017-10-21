	public static void process(AttributePath attributePath, StringBuilder sb) {
		if ( attributePath.getParent() != null ) {
			process( attributePath.getParent(), sb );
			if ( !"".equals( attributePath.getParent().getProperty() ) ) {
				sb.append( "_" );
			}
		}

		String property = attributePath.getProperty();
		property = property.replace( "<", "" );
		property = property.replace( ">", "" );

		sb.append( property );
	}
