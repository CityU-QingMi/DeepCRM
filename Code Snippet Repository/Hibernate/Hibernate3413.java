	@SuppressWarnings("")
	protected AttributeNodeImpl addAttributeNode(AttributeNodeImpl attributeNode) {
		if ( ! mutable ) {
			throw new IllegalStateException( "Entity/sub graph is not mutable" );
		}

		if ( attributeNodeMap == null ) {
			attributeNodeMap = new HashMap<>();
		}
		else {
			final AttributeNode old = attributeNodeMap.get( attributeNode.getRegistrationName() );
			if ( old != null ) {
				log.debugf(
						"Encountered request to add entity graph node [%s] using a name [%s] under which another " +
								"node is already registered [%s]",
						old.getClass().getName(),
						attributeNode.getRegistrationName(),
						attributeNode.getClass().getName()
				);
			}
		}
		attributeNodeMap.put( attributeNode.getRegistrationName(), attributeNode );

		return attributeNode;
	}
