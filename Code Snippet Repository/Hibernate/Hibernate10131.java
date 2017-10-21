	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String getterName = "get" + capitalizeFirst( fieldName );

		StringBuilder sb = new StringBuilder();
		sb.append( "public " ).append( fieldClass.getName() ).append( " " )
				.append( getterName ).append( "(){" ).append( "return (" ).append( fieldClass.getName() ).append(
				")this.theMap.get(\""
		)
				.append( fieldName ).append( "\")" ).append( ";" ).append( "}" );
		return CtMethod.make( sb.toString(), declaringClass );
	}
