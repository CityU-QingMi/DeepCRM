	private void getTemporal(List<Annotation> annotationList, Element element) {
		Element subElement = element != null ? element.element( "temporal" ) : null;
		if ( subElement != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( Temporal.class );
			String temporal = subElement.getTextTrim();
			if ( "DATE".equalsIgnoreCase( temporal ) ) {
				ad.setValue( "value", TemporalType.DATE );
			}
			else if ( "TIME".equalsIgnoreCase( temporal ) ) {
				ad.setValue( "value", TemporalType.TIME );
			}
			else if ( "TIMESTAMP".equalsIgnoreCase( temporal ) ) {
				ad.setValue( "value", TemporalType.TIMESTAMP );
			}
			else if ( StringHelper.isNotEmpty( temporal ) ) {
				throw new AnnotationException( "Unknown TemporalType: " + temporal + ". " + SCHEMA_VALIDATION );
			}
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}
