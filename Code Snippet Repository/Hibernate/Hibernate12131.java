	private static StringBuffer generateBody(MetaEntity entity, Context context) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter( sw );

			if ( context.addGeneratedAnnotation() ) {
				pw.println( writeGeneratedAnnotation( entity, context ) );
			}
			if ( context.isAddSuppressWarningsAnnotation() ) {
				pw.println( writeSuppressWarnings() );
			}

			pw.println( writeStaticMetaModelAnnotation( entity ) );

			printClassDeclaration( entity, pw, context );

			pw.println();

			List<MetaAttribute> members = entity.getMembers();
			for ( MetaAttribute metaMember : members ) {
				pw.println( "	" + metaMember.getDeclarationString() );
			}

			pw.println();
			pw.println( "}" );
			return sw.getBuffer();
		}
		finally {
			if ( pw != null ) {
				pw.close();
			}
		}
	}
