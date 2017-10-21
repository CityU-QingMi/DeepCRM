	@Test
	public void serializableMethodReplacerAndSuperclass() throws Exception {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(DELEGATION_OVERRIDES_CONTEXT);
		SerializableMethodReplacerCandidate s = (SerializableMethodReplacerCandidate) xbf.getBean("serializableReplacer");
		String forwards = "this is forwards";
		String backwards = new StringBuffer(forwards).reverse().toString();
		assertEquals(backwards, s.replaceMe(forwards));
		// SPR-356: lookup methods & method replacers are not serializable.
		assertFalse(
				"Lookup methods and method replacers are not meant to be serializable.",
				SerializationTestUtils.isSerializable(s));
	}
