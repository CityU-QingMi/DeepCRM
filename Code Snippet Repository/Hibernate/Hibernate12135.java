	private static String writeGeneratedAnnotation(MetaEntity entity, Context context) {
		StringBuilder generatedAnnotation = new StringBuilder();
		generatedAnnotation.append( "@" )
				.append( entity.importType( Generated.class.getName() ) )
				.append( "(value = \"" )
				.append( JPAMetaModelEntityProcessor.class.getName() );
		if ( context.addGeneratedDate() ) {
			generatedAnnotation.append( "\", date = \"" )
					.append( SIMPLE_DATE_FORMAT.get().format( new Date() ) )
					.append( "\")" );
		}
		else {
			generatedAnnotation.append( "\")" );
		}
		return generatedAnnotation.toString();
	}
