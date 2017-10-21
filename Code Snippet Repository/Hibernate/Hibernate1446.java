	private void getCascades(AnnotationDescriptor ad, Element element, XMLContext.Default defaults) {
		List<Element> elements = element != null ? element.elements( "cascade" ) : new ArrayList<Element>( 0 );
		List<CascadeType> cascades = new ArrayList<CascadeType>();
		for ( Element subelement : elements ) {
			if ( subelement.element( "cascade-all" ) != null ) {
				cascades.add( CascadeType.ALL );
			}
			if ( subelement.element( "cascade-persist" ) != null ) {
				cascades.add( CascadeType.PERSIST );
			}
			if ( subelement.element( "cascade-merge" ) != null ) {
				cascades.add( CascadeType.MERGE );
			}
			if ( subelement.element( "cascade-remove" ) != null ) {
				cascades.add( CascadeType.REMOVE );
			}
			if ( subelement.element( "cascade-refresh" ) != null ) {
				cascades.add( CascadeType.REFRESH );
			}
			if ( subelement.element( "cascade-detach" ) != null ) {
				cascades.add( CascadeType.DETACH );
			}
		}
		if ( Boolean.TRUE.equals( defaults.getCascadePersist() )
				&& !cascades.contains( CascadeType.ALL ) && !cascades.contains( CascadeType.PERSIST ) ) {
			cascades.add( CascadeType.PERSIST );
		}
		if ( cascades.size() > 0 ) {
			ad.setValue( "cascade", cascades.toArray( new CascadeType[cascades.size()] ) );
		}
	}
