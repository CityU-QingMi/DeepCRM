	@Test
	public void setPropertyIsReflectedImmediately() {
		TestBean target = new TestBean();
		int newAge = 33;
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			target.setAge(newAge);
			Object bwAge = accessor.getPropertyValue("age");
			assertTrue("Age is an integer", bwAge instanceof Integer);
			assertTrue("Bean wrapper must pick up changes", (int) bwAge == newAge);
		}
		catch (Exception ex) {
			fail("Shouldn't throw exception when everything is valid");
		}
	}
