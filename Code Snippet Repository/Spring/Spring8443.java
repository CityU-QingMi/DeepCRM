	@Test
	public void valueAndScriptsDeclared() throws Exception {
		Class<?> clazz = ValueAndScriptsDeclared.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));

		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(either(
				containsString("attribute 'value' and its alias 'scripts'")).or(
				containsString("attribute 'scripts' and its alias 'value'")));
		exception.expectMessage(either(containsString("values of [{foo}] and [{bar}]")).or(
				containsString("values of [{bar}] and [{foo}]")));
		exception.expectMessage(containsString("but only one is permitted"));
		listener.beforeTestMethod(testContext);
	}
