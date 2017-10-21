	private Object newInstance(Class type) throws Exception {
		final BulkAccessor instance = (BulkAccessor) type.newInstance();
		instance.target = targetBean;
		final int len = getterNames.length;
		instance.getters = new String[len];
		instance.setters = new String[len];
		instance.types = new Class[len];
		for ( int i = 0; i < len; i++ ) {
			instance.getters[i] = getterNames[i];
			instance.setters[i] = setterNames[i];
			instance.types[i] = types[i];
		}

		return instance;
	}
