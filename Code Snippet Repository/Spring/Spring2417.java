	public WebSphereClassPreDefinePlugin(ClassFileTransformer transformer) {
		this.transformer = transformer;
		ClassLoader classLoader = transformer.getClass().getClassLoader();

		// First force the full class loading of the weaver by invoking transformation on a dummy class
		try {
			String dummyClass = Dummy.class.getName().replace('.', '/');
			byte[] bytes = FileCopyUtils.copyToByteArray(classLoader.getResourceAsStream(dummyClass + ".class"));
			transformer.transform(classLoader, dummyClass, null, null, bytes);
		}
		catch (Throwable ex) {
			throw new IllegalArgumentException("Cannot load transformer", ex);
		}
	}
