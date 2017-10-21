	private void doResultTransformerTest(ResultTransformer transformer, boolean isSingleton) {
		Map transformerMap = new HashMap();

		transformerMap.put( transformer, "" );
		assert transformerMap.size() == 1 : "really messed up";
		Object old = transformerMap.put( transformer, "value" );
		assert old != null && transformerMap.size() == 1 : "apparent QueryKey equals/hashCode issue";

		// finally, lets serialize it and see what happens
		ResultTransformer transformer2 = ( ResultTransformer ) SerializationHelper.clone( transformer );
		old = transformerMap.put( transformer2, "new value" );
		assert old != null && transformerMap.size() == 1 : "deserialization did not set hashCode or equals properly";
		if ( isSingleton ) {
			assert transformer == transformer2: "deserialization issue for singleton transformer";
		}
		else {
			assert transformer != transformer2: "deserialization issue for non-singleton transformer";
		}
		assert transformer.equals( transformer2 ): "deep copy issue";
	}
