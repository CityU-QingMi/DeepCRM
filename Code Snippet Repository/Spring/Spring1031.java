	@Test
	public void testCopyPropertiesWithIgnore() throws Exception {
		TestBean tb = new TestBean();
		assertTrue("Name empty", tb.getName() == null);
		tb.setAge(32);
		tb.setTouchy("bla");
		TestBean tb2 = new TestBean();
		tb2.setName("rod");
		assertTrue("Age empty", tb2.getAge() == 0);
		assertTrue("Touchy empty", tb2.getTouchy() == null);

		// "spouse", "touchy", "age" should not be copied
		BeanUtils.copyProperties(tb, tb2, "spouse", "touchy", "age");
		assertTrue("Name copied", tb2.getName() == null);
		assertTrue("Age still empty", tb2.getAge() == 0);
		assertTrue("Touchy still empty", tb2.getTouchy() == null);
	}
