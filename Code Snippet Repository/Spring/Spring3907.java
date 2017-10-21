	@Test
	public void fixedRateTasks() {
		List<IntervalTask> tasks = (List<IntervalTask>) new DirectFieldAccessor(
				this.registrar).getPropertyValue("fixedRateTasks");
		assertEquals(3, tasks.size());
		assertEquals(1000L, tasks.get(0).getInterval());
		assertEquals(2000L, tasks.get(1).getInterval());
		assertEquals(4000L, tasks.get(2).getInterval());
		assertEquals(500, tasks.get(2).getInitialDelay());
	}
