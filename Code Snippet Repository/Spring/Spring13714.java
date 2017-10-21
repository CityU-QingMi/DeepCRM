	@Test
	public void parentLoader() throws Exception {

		this.configurer.setApplicationContext(this.applicationContext);

		ClassLoader classLoader = this.configurer.createTemplateClassLoader();
		assertNotNull(classLoader);
		URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
		assertThat(Arrays.asList(urlClassLoader.getURLs()), Matchers.hasSize(1));
		assertThat(Arrays.asList(urlClassLoader.getURLs()).get(0).toString(),
				Matchers.endsWith("org/springframework/web/servlet/view/groovy/"));

		this.configurer.setResourceLoaderPath(RESOURCE_LOADER_PATH + ",classpath:org/springframework/web/servlet/view/");
		classLoader = this.configurer.createTemplateClassLoader();
		assertNotNull(classLoader);
		urlClassLoader = (URLClassLoader) classLoader;
		assertThat(Arrays.asList(urlClassLoader.getURLs()), Matchers.hasSize(2));
		assertThat(Arrays.asList(urlClassLoader.getURLs()).get(0).toString(),
				Matchers.endsWith("org/springframework/web/servlet/view/groovy/"));
		assertThat(Arrays.asList(urlClassLoader.getURLs()).get(1).toString(),
				Matchers.endsWith("org/springframework/web/servlet/view/"));
	}
