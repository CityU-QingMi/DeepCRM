	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String setterName = "set" + capitalizeFirst( fieldName );

		StringBuilder sb = new StringBuilder();
		sb.append( "public void " ).append( setterName ).append( "(" )
				.append( fieldClass.getName() ).append( " " ).append( fieldName )
				.append( ")" ).append( "{" ).append( "this.theMap.put(\"" ).append( fieldName )
				.append( "\"," ).append( fieldName ).append( ")" ).append( ";" ).append( "}" );
		return CtMethod.make( sb.toString(), declaringClass );
	}
