	private void getEnumerated(List<Annotation> annotationList, Element element) {
		Element subElement = element != null ? element.element( "enumerated" ) : null;
		if ( subElement != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Enumerated.class );
			String enumerated = subElement.getTextTrim();
			if ( "ORDINAL".equalsIgnoreCase( enumerated ) ) {
				ad.setValue( "value", EnumType.ORDINAL );
			}
			else if ( "STRING".equalsIgnoreCase( enumerated ) ) {
				ad.setValue( "value", EnumType.STRING );
			}
			else if ( StringHelper.isNotEmpty( enumerated ) ) {
				throw new AnnotationException( "Unknown EnumType: " + enumerated + ". " + SCHEMA_VALIDATION );
			}
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}
