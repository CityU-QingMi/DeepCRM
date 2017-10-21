	private PrimaryKeyJoinColumn[] buildPrimaryKeyJoinColumns(Element element) {
		if ( element == null ) {
			return new PrimaryKeyJoinColumn[] { };
		}
		List pkJoinColumnElementList = element.elements( "primary-key-join-column" );
		PrimaryKeyJoinColumn[] pkJoinColumns = new PrimaryKeyJoinColumn[pkJoinColumnElementList.size()];
		int index = 0;
		Iterator pkIt = pkJoinColumnElementList.listIterator();
		while ( pkIt.hasNext() ) {
			Element subelement = (Element) pkIt.next();
			AnnotationDescriptor pkAnn = new AnnotationDescriptor( PrimaryKeyJoinColumn.class );
			copyStringAttribute( pkAnn, subelement, "name", false );
			copyStringAttribute( pkAnn, subelement, "referenced-column-name", false );
			copyStringAttribute( pkAnn, subelement, "column-definition", false );
			pkJoinColumns[index++] = AnnotationFactory.create( pkAnn );
		}
		return pkJoinColumns;
	}
