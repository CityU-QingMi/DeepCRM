	private static void buildIndex(AnnotationDescriptor annotation, Element element){
		List indexElementList = element.elements( "index" );
		Index[] indexes = new Index[indexElementList.size()];
		for(int i=0;i<indexElementList.size();i++){
			Element subelement = (Element)indexElementList.get( i );
			AnnotationDescriptor indexAnn = new AnnotationDescriptor( Index.class );
			copyStringAttribute( indexAnn, subelement, "name", false );
			copyStringAttribute( indexAnn, subelement, "column-list", true );
			copyBooleanAttribute( indexAnn, subelement, "unique" );
			indexes[i] = AnnotationFactory.create( indexAnn );
		}
		annotation.setValue( "indexes", indexes );
	}
