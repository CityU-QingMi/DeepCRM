	@Before
	public void setup() throws Exception {
		this.context = new GenericApplicationContext();
		this.context.refresh();

		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setPreferFileSystemAccess(false);
		configurer.setTemplateLoaderPath(TEMPLATE_PATH);
		configurer.setResourceLoader(this.context);
		this.freeMarkerConfig = configurer.createConfiguration();
	}
