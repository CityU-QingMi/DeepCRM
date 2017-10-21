	@Test
	public void handlerBeanNotFound() throws Exception {
		MockServletContext sc = new MockServletContext("");
		XmlWebApplicationContext root = new XmlWebApplicationContext();
		root.setServletContext(sc);
		root.setConfigLocations(new String[] {"/org/springframework/web/servlet/handler/map1.xml"});
		root.refresh();
		XmlWebApplicationContext wac = new XmlWebApplicationContext();
		wac.setParent(root);
		wac.setServletContext(sc);
		wac.setNamespace("map2err");
		wac.setConfigLocations(new String[] {"/org/springframework/web/servlet/handler/map2err.xml"});
		try {
			wac.refresh();
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (FatalBeanException ex) {
			NoSuchBeanDefinitionException nestedEx = (NoSuchBeanDefinitionException) ex.getCause();
			assertEquals("mainControlle", nestedEx.getBeanName());
		}
	}
