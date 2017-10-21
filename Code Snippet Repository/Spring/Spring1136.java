	@Before
	public void setUp() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(CONTEXT);
		factory.addPropertyEditorRegistrar(new PropertyEditorRegistrar() {
			@Override
			public void registerCustomEditors(PropertyEditorRegistry registry) {
				registry.registerCustomEditor(Date.class, new CustomDateEditor((DateFormat) DATE_FORMAT.clone(), false));
			}
		});
		this.factory = factory;
	}
