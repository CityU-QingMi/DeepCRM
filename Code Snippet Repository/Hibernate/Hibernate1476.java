	private static void bindNamedAttributeNodes(Element subElement, AnnotationDescriptor ann) {
		List<Element> namedAttributeNodes = subElement.elements("named-attribute-node");
		List<NamedAttributeNode> annNamedAttributeNodes = new ArrayList<NamedAttributeNode>(  );
		for(Element namedAttributeNode : namedAttributeNodes){
			AnnotationDescriptor annNamedAttributeNode = new AnnotationDescriptor( NamedAttributeNode.class );
			copyStringAttribute( annNamedAttributeNode, namedAttributeNode, "value", "name", true );
			copyStringAttribute( annNamedAttributeNode, namedAttributeNode, "subgraph", false );
			copyStringAttribute( annNamedAttributeNode, namedAttributeNode, "key-subgraph", false );
			annNamedAttributeNodes.add( (NamedAttributeNode) AnnotationFactory.create( annNamedAttributeNode ) );
		}
		ann.setValue( "attributeNodes", annNamedAttributeNodes.toArray( new NamedAttributeNode[annNamedAttributeNodes.size()] ) );
	}
