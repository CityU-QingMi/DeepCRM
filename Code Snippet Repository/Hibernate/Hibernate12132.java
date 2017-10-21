	private static void printClassDeclaration(MetaEntity entity, PrintWriter pw, Context context) {
		pw.print( "public abstract class " + entity.getSimpleName() + META_MODEL_CLASS_NAME_SUFFIX );

		String superClassName = findMappedSuperClass( entity, context );
		if ( superClassName != null ) {
			pw.print( " extends " + superClassName + META_MODEL_CLASS_NAME_SUFFIX );
		}

		pw.println( " {" );
	}
