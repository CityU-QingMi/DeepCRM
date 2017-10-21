	@Test
	public void testCustomEditorForAllNestedStringProperties() {
		TestBean tb = new TestBean();
		tb.setSpouse(new TestBean());
		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue("prefix" + text);
			}
		});
		bw.setPropertyValue("spouse.name", "value");
		bw.setPropertyValue("touchy", "value");
		assertEquals("prefixvalue", bw.getPropertyValue("spouse.name"));
		assertEquals("prefixvalue", tb.getSpouse().getName());
		assertEquals("prefixvalue", bw.getPropertyValue("touchy"));
		assertEquals("prefixvalue", tb.getTouchy());
	}
