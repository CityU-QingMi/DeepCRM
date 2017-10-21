	@Test
	public void validUsageNotKeepingTaskList() throws Exception {
		sw.setKeepTaskList(false);
		long int1 = 166L;
		long int2 = 45L;
		String name1 = "Task 1";
		String name2 = "Task 2";

		assertFalse(sw.isRunning());
		sw.start(name1);
		Thread.sleep(int1);
		assertTrue(sw.isRunning());
		sw.stop();

		// TODO are timings off in JUnit? Why do these assertions sometimes fail
		// under both Ant and Eclipse?

		// long fudgeFactor = 5L;
		// assertTrue("Unexpected timing " + sw.getTotalTime(), sw.getTotalTime() >=
		// int1);
		// assertTrue("Unexpected timing " + sw.getTotalTime(), sw.getTotalTime() <= int1
		// + fudgeFactor);
		sw.start(name2);
		Thread.sleep(int2);
		sw.stop();
		// assertTrue("Unexpected timing " + sw.getTotalTime(), sw.getTotalTime() >= int1
		// + int2);
		// assertTrue("Unexpected timing " + sw.getTotalTime(), sw.getTotalTime() <= int1
		// + int2 + fudgeFactor);

		assertTrue(sw.getTaskCount() == 2);
		String pp = sw.prettyPrint();
		assertTrue(pp.contains("kept"));

		String toString = sw.toString();
		assertFalse(toString.contains(name1));
		assertFalse(toString.contains(name2));

		exception.expect(UnsupportedOperationException.class);
		sw.getTaskInfo();
	}
