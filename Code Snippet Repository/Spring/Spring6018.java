	private void reportPerformance(String title, long interpretedTotal, long compiledTotal) {
		double averageInterpreted = interpretedTotal / iterations;
		double averageCompiled = compiledTotal / iterations;
		double ratio = (averageCompiled / averageInterpreted) * 100.0d;
		logln(">>" + title + ": average for " + count + ": compiled=" + averageCompiled +
				"ms interpreted=" + averageInterpreted + "ms: compiled takes " +
				((int) ratio) + "% of the interpreted time");
		if (averageCompiled > averageInterpreted) {
			fail("Compiled version took longer than interpreted! CompiledSpeed=~" + averageCompiled +
					"ms InterpretedSpeed=" + averageInterpreted + "ms");
		}
		logln();
	}
