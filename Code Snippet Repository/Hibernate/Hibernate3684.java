	protected Map<String, AttributeNodeImplementor> buildAttributeNodeMap() {
		GraphNodeImplementor graphNode = graphStack.peekLast();
		List<AttributeNodeImplementor<?>> attributeNodeImplementors = graphNode.attributeImplementorNodes();
		Map<String, AttributeNodeImplementor> attributeNodeImplementorMap = attributeNodeImplementors.isEmpty() ? Collections
				.<String, AttributeNodeImplementor>emptyMap() : new HashMap<String, AttributeNodeImplementor>(
				attributeNodeImplementors.size()
		);
		for ( AttributeNodeImplementor attribute : attributeNodeImplementors ) {
			attributeNodeImplementorMap.put( attribute.getAttributeName(), attribute );
		}
		return attributeNodeImplementorMap;
	}
