	private List<DynamicNode> scanContainerClassAndCreateDynamicTests(Class<?> containerClass) {
		String containerName = containerClass.getSimpleName();
		List<DynamicNode> nodes = new ArrayList<>();
		Map<Details, List<DynamicTest>> map = new EnumMap<>(Details.class);
		for (Method method : findMethods(containerClass, m -> m.isAnnotationPresent(Test.class))) {
			String methodName = method.getName();
			Class<?>[] types = method.getParameterTypes();
			for (Details details : Details.values()) {
				List<DynamicTest> tests = map.computeIfAbsent(details, key -> new ArrayList<>());
				for (Theme theme : Theme.values()) {
					String caption = containerName + "-" + methodName + "-" + details + "-" + theme;
					String[] args = { //
							"--include-engine", "junit-jupiter", //
							"--details", details.name(), //
							"--details-theme", theme.name(), //
							"--disable-ansi-colors", "true", //
							"--include-classname", containerClass.getCanonicalName(), //
							"--select-method", getFullyQualifiedMethodName(containerClass, methodName, types) //
					};
					String displayName = methodName + "() " + theme.name();
					String dirName = "console/details/" + containerName.toLowerCase();
					String outName = caption + ".out.txt";
					tests.add(DynamicTest.dynamicTest(displayName, new Runner(dirName, outName, args)));
				}
			}
		}
		map.forEach((details, tests) -> nodes.add(DynamicContainer.dynamicContainer(details.name(), tests)));
		return nodes;
	}
