	@Ignore("")
	@Test
	public void classesWithoutDebugSymbols() throws Exception {
		// JDK classes don't have debug information (usually)
		Class<Component> clazz = Component.class;
		String methodName = "list";

		Method m = clazz.getMethod(methodName);
		String[] names = discoverer.getParameterNames(m);
		assertNull(names);

		m = clazz.getMethod(methodName, PrintStream.class);
		names = discoverer.getParameterNames(m);
		assertNull(names);

		m = clazz.getMethod(methodName, PrintStream.class, int.class);
		names = discoverer.getParameterNames(m);
		assertNull(names);
	}
