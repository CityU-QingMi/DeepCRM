	ApiReport generateReport(String... packages) {
		final Logger logger = LoggerFactory.getLogger(ApiReportGenerator.class);
		final String EOL = System.lineSeparator();

		// Scan packages
		ScanResult scanResult = new FastClasspathScanner(packages).scan();

		// Collect names
		List<String> names = new ArrayList<>();
		names.addAll(scanResult.getNamesOfClassesWithAnnotation(API.class));
		names.addAll(scanResult.getNamesOfAnnotationsWithMetaAnnotation(API.class));

		logger.debug(() -> {
			StringBuilder builder = new StringBuilder(
				names.size() + " @API declarations (including meta) found in class-path:");
			builder.append(EOL);
			scanResult.getUniqueClasspathElements().forEach(e -> builder.append(e).append(EOL));
			return builder.toString();

		});

		// Collect types
		List<Class<?>> types = scanResult.classNamesToClassRefs(names);
		// only retain directly annotated types
		types.removeIf(c -> !c.isAnnotationPresent(API.class));
		types.sort(Comparator.comparing(type -> type.getName()));

		logger.debug(() -> {
			StringBuilder builder = new StringBuilder("Listing of all " + types.size() + " annotated types:");
			builder.append(EOL);
			types.forEach(e -> builder.append(e).append(EOL));
			return builder.toString();
		});

		// Build map
		Map<Status, List<Class<?>>> declarationsMap = new EnumMap<>(Status.class);
		for (Status status : Status.values()) {
			declarationsMap.put(status, new ArrayList<>());
		}
		types.forEach(type -> declarationsMap.get(type.getAnnotation(API.class).status()).add(type));

		// Create report
		return new ApiReport(types, declarationsMap);
	}
