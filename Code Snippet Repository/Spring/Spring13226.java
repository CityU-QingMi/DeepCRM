	@Test
	public void addAttributeCustomType() {
		String attrName = "person";
		this.redirectAttributes.addAttribute(attrName, new TestBean("Fred"));

		assertEquals("ConversionService should have invoked toString()", "Fred", this.redirectAttributes.get(attrName));

		this.conversionService.addConverter(new TestBeanConverter());
		this.redirectAttributes.addAttribute(attrName, new TestBean("Fred"));

		assertEquals("Type converter should have been used", "[Fred]", this.redirectAttributes.get(attrName));
	}
