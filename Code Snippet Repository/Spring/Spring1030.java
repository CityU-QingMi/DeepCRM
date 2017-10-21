	@Test
	public void testCopyPropertiesWithEditable() throws Exception {
		TestBean tb = new TestBean();
		assertTrue("Name empty", tb.getName() == null);
		tb.setAge(32);
		tb.setTouchy("bla");
		TestBean tb2 = new TestBean();
		tb2.setName("rod");
		assertTrue("Age empty", tb2.getAge() == 0);
		assertTrue("Touchy empty", tb2.getTouchy() == null);

		// "touchy" should not be copied: it's not defined in ITestBean
		BeanUtils.copyProperties(tb, tb2, ITestBean.class);
		assertTrue("Name copied", tb2.getName() == null);
		assertTrue("Age copied", tb2.getAge() == 32);
		assertTrue("Touchy still empty", tb2.getTouchy() == null);
	}
