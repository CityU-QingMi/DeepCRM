	@Test
	public void setEmptyPropertyValues() {
		TestBean target = new TestBean();
		int age = 50;
		String name = "Tony";
		target.setAge(age);
		target.setName(name);
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			assertTrue("age is OK", target.getAge() == age);
			assertTrue("name is OK", name.equals(target.getName()));
			accessor.setPropertyValues(new MutablePropertyValues());
			// Check its unchanged
			assertTrue("age is OK", target.getAge() == age);
			assertTrue("name is OK", name.equals(target.getName()));
		}
		catch (BeansException ex) {
			fail("Shouldn't throw exception when everything is valid");
		}
	}
