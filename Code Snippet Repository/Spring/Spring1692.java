	@Test
	public void testComplexObject() {
		ValueBean bean = new ValueBean();
		BeanWrapper bw = new BeanWrapperImpl(bean);
		Integer value = new Integer(1);

		bw.setPropertyValue("value", value);
		assertEquals("value not set correctly", bean.getValue(), value);

		value = new Integer(2);
		bw.setPropertyValue("value", value.toString());
		assertEquals("value not converted", bean.getValue(), value);

		bw.setPropertyValue("value", null);
		assertNull("value not null", bean.getValue());

		bw.setPropertyValue("value", "");
		assertNull("value not converted to null", bean.getValue());
	}
