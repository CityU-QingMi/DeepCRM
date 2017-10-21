	private void doTestWithClass(AutoPopulatingList<Object> list) {
		Object lastElement = null;
		for (int x = 0; x < 10; x++) {
			Object element = list.get(x);
			assertNotNull("Element is null", list.get(x));
			assertTrue("Element is incorrect type", element instanceof TestObject);
			assertNotSame(lastElement, element);
			lastElement = element;
		}

		String helloWorld = "Hello World!";
		list.add(10, null);
		list.add(11, helloWorld);
		assertEquals(helloWorld, list.get(11));

		assertTrue(list.get(10) instanceof TestObject);
		assertTrue(list.get(12) instanceof TestObject);
		assertTrue(list.get(13) instanceof TestObject);
		assertTrue(list.get(20) instanceof TestObject);
	}
