	public String getDeclarationString() {
		return new StringBuilder().append( "public static volatile " )
				.append( parent.importType( getMetaType() ) )
				.append( "<" )
				.append( parent.importType( parent.getQualifiedName() ) )
				.append( ", " )
				.append( parent.importType( getTypeDeclaration() ) )
				.append( "> " )
				.append( getPropertyName() )
				.append( ";" )
				.toString();
	}
