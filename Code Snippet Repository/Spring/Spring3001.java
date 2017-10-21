	@Test
	public void testFileSystemResourceWithImport() throws URISyntaxException {
		String file = getClass().getResource(RESOURCE_CONTEXT.getPath()).toURI().getPath();
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(new FileSystemResource(file));
		// comes from "resourceImport.xml"
		xbf.getBean("resource1", ResourceTestBean.class);
		// comes from "resource.xml"
		xbf.getBean("resource2", ResourceTestBean.class);
	}
