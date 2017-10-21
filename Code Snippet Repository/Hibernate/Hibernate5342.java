	private void doTest(QueryKey key) {
		Map keyMap = new HashMap();
		Map transformerMap = new HashMap();

		keyMap.put( key, "" );
		assert keyMap.size() == 1 : "really messed up";
		Object old = keyMap.put( key, "value" );
		assert old != null && keyMap.size() == 1 : "apparent QueryKey equals/hashCode issue";

		if ( key.getResultTransformer() != null ) {
			transformerMap.put( key.getResultTransformer(), "" );
			assert transformerMap.size() == 1 : "really messed up";
			old = transformerMap.put( key.getResultTransformer(), "value" );
			assert old != null && transformerMap.size() == 1 : "apparent QueryKey equals/hashCode issue";
		}

		// finally, lets serialize it and see what happens
		QueryKey key2 = ( QueryKey ) SerializationHelper.clone( key );
		assert key != key2 : "deep copy issue";
		old = keyMap.put( key2, "new value" );
		assert old != null && keyMap.size() == 1 : "deserialization did not set hashCode or equals properly";
		if ( key.getResultTransformer() == null ) {
			assert key2.getResultTransformer() == null;
		}
		else {
			old = transformerMap.put( key2.getResultTransformer(), "new value" );
			assert old != null && transformerMap.size() == 1 : "deserialization did not set hashCode or equals properly";
				assert key.getResultTransformer() != key2.getResultTransformer(): "deserialization issue for non-singleton transformer";
				assert key.getResultTransformer().equals( key2.getResultTransformer() ): "deep copy issue";
		}
	}
