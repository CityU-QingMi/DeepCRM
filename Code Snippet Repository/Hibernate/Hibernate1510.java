	private static void buildUniqueConstraints(AnnotationDescriptor annotation, Element element) {
		List uniqueConstraintElementList = element.elements( "unique-constraint" );
		UniqueConstraint[] uniqueConstraints = new UniqueConstraint[uniqueConstraintElementList.size()];
		int ucIndex = 0;
		Iterator ucIt = uniqueConstraintElementList.listIterator();
		while ( ucIt.hasNext() ) {
			Element subelement = (Element) ucIt.next();
			List<Element> columnNamesElements = subelement.elements( "column-name" );
			String[] columnNames = new String[columnNamesElements.size()];
			int columnNameIndex = 0;
			Iterator it = columnNamesElements.listIterator();
			while ( it.hasNext() ) {
				Element columnNameElt = (Element) it.next();
				columnNames[columnNameIndex++] = columnNameElt.getTextTrim();
			}
			AnnotationDescriptor ucAnn = new AnnotationDescriptor( UniqueConstraint.class );
			copyStringAttribute( ucAnn, subelement, "name", false );
			ucAnn.setValue( "columnNames", columnNames );
			uniqueConstraints[ucIndex++] = AnnotationFactory.create( ucAnn );
		}
		annotation.setValue( "uniqueConstraints", uniqueConstraints );
	}
