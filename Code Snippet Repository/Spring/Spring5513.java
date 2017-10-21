	@Test
	public void validUsage() throws Exception {
		String id = "myId";
		StopWatch sw = new StopWatch(id);
		long int1 = 166L;
		long int2 = 45L;
		String name1 = "Task 1";
		String name2 = "Task 2";

		assertFalse(sw.isRunning());
		sw.start(name1);
		Thread.sleep(int1);
		assertTrue(sw.isRunning());
		assertEquals(name1, sw.currentTaskName());
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
		assertTrue(pp.contains(name1));
		assertTrue(pp.contains(name2));

		StopWatch.TaskInfo[] tasks = sw.getTaskInfo();
		assertTrue(tasks.length == 2);
		assertTrue(tasks[0].getTaskName().equals(name1));
		assertTrue(tasks[1].getTaskName().equals(name2));

		String toString = sw.toString();
		assertTrue(toString.contains(id));
		assertTrue(toString.contains(name1));
		assertTrue(toString.contains(name2));

		assertEquals(id, sw.getId());
	}
