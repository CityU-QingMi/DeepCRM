	@Override
	public boolean startingAttribute(AttributeDefinition attributeDefinition) {
		Map<String, AttributeNodeImplementor> attributeMap = attributeMapStack.peekLast();
		final String attrName = attributeDefinition.getName();
		AttributeNodeImplementor attributeNode = NON_EXIST_ATTRIBUTE_NODE;
		GraphNodeImplementor subGraphNode = NON_EXIST_SUBGRAPH_NODE;
		//the attribute is in the EntityGraph, so, let's continue
		if ( attributeMap.containsKey( attrName ) ) {
			attributeNode = attributeMap.get( attrName );
			//here we need to check if there is a subgraph (or sub key graph if it is an indexed attribute )
			Map<Class, Subgraph> subGraphs = attributeNode.getSubgraphs();
			Class javaType = attributeDefinition.getType().getReturnedClass();
			if ( !subGraphs.isEmpty() && subGraphs.containsKey( javaType ) ) {
				subGraphNode = (GraphNodeImplementor) subGraphs.get( javaType );
			}

		}
		attributeStack.addLast( attributeNode );
		graphStack.addLast( subGraphNode );
		return super.startingAttribute( attributeDefinition );
	}
