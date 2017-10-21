	private static CtConstructor generateConstructor(String className, CtClass cc)
			throws NotFoundException, CannotCompileException {
		StringBuffer sb = new StringBuffer();
		sb.append( "public " )
				.append( getLastComponent( className ) )
				.append( "(" )
				.append( Map.class.getName() )
				.append( " map)" )
				.append( "{" )
				.append( "this.theMap = map;" )
				.append( "}" );
		return CtNewConstructor.make( sb.toString(), cc );
	}
