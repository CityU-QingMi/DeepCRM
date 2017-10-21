	private void getFetchType(AnnotationDescriptor descriptor, Element element) {
		String fetchString = element != null ? element.attributeValue( "fetch" ) : null;
		if ( fetchString != null ) {
			if ( "eager".equalsIgnoreCase( fetchString ) ) {
				descriptor.setValue( "fetch", FetchType.EAGER );
			}
			else if ( "lazy".equalsIgnoreCase( fetchString ) ) {
				descriptor.setValue( "fetch", FetchType.LAZY );
			}
		}
	}
