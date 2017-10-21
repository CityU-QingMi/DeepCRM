	protected Method getMethod(String name) {
		// assumes no overloading of test methods...
		Method[] candidates = this.getClass().getMethods();
		for (Method candidate : candidates) {
			if (candidate.getName().equals(name)) {
				return candidate;
			}
		}
		fail("Bad test specification, no method '" + name + "' found in test class");
		return null;
	}
