	@Test
	public void testComplexObjectWithOldValueAccess() {
		TestBean tb = new TestBean();
		String newName = "Rod";
		String tbString = "Kerry_34";

		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.setExtractOldValueForEditor(true);
		bw.registerCustomEditor(ITestBean.class, new OldValueAccessingTestBeanEditor());
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("age", new Integer(55)));
		pvs.addPropertyValue(new PropertyValue("name", newName));
		pvs.addPropertyValue(new PropertyValue("touchy", "valid"));
		pvs.addPropertyValue(new PropertyValue("spouse", tbString));

		bw.setPropertyValues(pvs);
		assertTrue("spouse is non-null", tb.getSpouse() != null);
		assertTrue("spouse name is Kerry and age is 34",
				tb.getSpouse().getName().equals("Kerry") && tb.getSpouse().getAge() == 34);
		ITestBean spouse = tb.getSpouse();

		bw.setPropertyValues(pvs);
		assertSame("Should have remained same object", spouse, tb.getSpouse());
	}
