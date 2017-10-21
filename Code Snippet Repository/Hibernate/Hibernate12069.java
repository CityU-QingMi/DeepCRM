	private void createRunnersForParameters(Iterable<Object[]> allParameters, String namePattern) throws Exception {
		int i = 0;
		for (Object[] parametersOfSingleTest : allParameters) {
			String name = nameFor(namePattern, i, parametersOfSingleTest);
			CustomRunnerForParameters runner = new CustomRunnerForParameters(
					getTestClass().getJavaClass(), parametersOfSingleTest,
					name);
			runners.add(runner);
			++i;
		}
	}
