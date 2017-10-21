	private void mapEnumerationType(Element parent, Type type, Properties parameters) {
		if ( parameters.getProperty( EnumType.ENUM ) != null ) {
			parent.addElement( "param" )
					.addAttribute( "name", EnumType.ENUM )
					.setText( parameters.getProperty( EnumType.ENUM ) );
		}
		else {
			parent.addElement( "param" )
					.addAttribute( "name", EnumType.ENUM )
					.setText( type.getReturnedClass().getName() );
		}
		if ( parameters.getProperty( EnumType.NAMED ) != null ) {
			parent.addElement( "param" )
					.addAttribute( "name", EnumType.NAMED )
					.setText( parameters.getProperty( EnumType.NAMED ) );
		}
		else {
			parent.addElement( "param" )
					.addAttribute( "name", EnumType.NAMED )
					.setText( "" + !( (EnumType) ( (CustomType) type ).getUserType() ).isOrdinal() );
		}
	}
