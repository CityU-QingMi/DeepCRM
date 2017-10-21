	@Test
	public void voidMethodInAsyncClassGetsRoutedAsynchronously() {
		Assume.group(TestGroup.PERFORMANCE);

		ClassWithAsyncAnnotation obj = new ClassWithAsyncAnnotation();
		obj.increment();
		executor.waitForCompletion();
		assertEquals(1, obj.counter);
		assertEquals(1, executor.submitStartCounter);
		assertEquals(1, executor.submitCompleteCounter);
	}
