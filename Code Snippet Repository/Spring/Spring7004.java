	@Test
	public void runnableIsInvokedEvenIfContainerIsNotRunning() throws InterruptedException {
		DefaultMessageListenerContainer container = createRunningContainer();
		container.stop();

		// container is stopped but should nevertheless invoke the runnable argument
		TestRunnable runnable2 = new TestRunnable();
		container.stop(runnable2);
		runnable2.waitForCompletion();
	}
