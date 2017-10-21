	@Override
	public String getDisplayText() {
		StringBuilder buf = new StringBuilder();
		FromElement fromElement = getFromElement();
		buf.append( "{propertyName=" ).append( propertyName );
		buf.append( ",dereferenceType=" ).append( dereferenceType.name() );
		buf.append( ",getPropertyPath=" ).append( propertyPath );
		buf.append( ",path=" ).append( getPath() );
		if ( fromElement != null ) {
			buf.append( ",tableAlias=" ).append( fromElement.getTableAlias() );
			buf.append( ",className=" ).append( fromElement.getClassName() );
			buf.append( ",classAlias=" ).append( fromElement.getClassAlias() );
		}
		else {
			buf.append( ",no from element" );
		}
		buf.append( '}' );
		return buf.toString();
	}
