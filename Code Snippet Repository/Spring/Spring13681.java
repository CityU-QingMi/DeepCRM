	@Test
	public void testXmlViewResolverDefaultLocation() {
		StaticWebApplicationContext wac = new StaticWebApplicationContext() {
			@Override
			protected Resource getResourceByPath(String path) {
				assertTrue("Correct default location", XmlViewResolver.DEFAULT_LOCATION.equals(path));
				return super.getResourceByPath(path);
			}
		};
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		XmlViewResolver vr = new XmlViewResolver();
		try {
			vr.setApplicationContext(wac);
			vr.afterPropertiesSet();
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			// expected
		}
	}
