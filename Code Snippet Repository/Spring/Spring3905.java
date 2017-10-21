	@Test(timeout = 5000)
	public void checkTarget() {
		Task task =
			new GenericXmlApplicationContext(
					LazyScheduledTasksBeanDefinitionParserTests.class,
					"lazyScheduledTasksContext.xml")
				.getBean(Task.class);

		while (!task.executed) {
			try {
				Thread.sleep(10);
			}
			catch (Exception ex) { /* Do Nothing */ }
		}
	}
