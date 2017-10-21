	@Test
	public void testGenericSetWithConversionFailure() {
		GenericBean<?> gb = new GenericBean<>();
		BeanWrapper bw = new BeanWrapperImpl(gb);
		Set<TestBean> input = new HashSet<>();
		input.add(new TestBean());
		try {
			bw.setPropertyValue("integerSet", input);
			fail("Should have thrown TypeMismatchException");
		}
		catch (TypeMismatchException ex) {
			assertTrue(ex.getMessage().indexOf("java.lang.Integer") != -1);
		}
	}
