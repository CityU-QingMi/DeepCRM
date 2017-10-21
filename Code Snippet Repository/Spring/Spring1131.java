	@Before
	public void setUp() {
		// Interesting hierarchical factory to test counts.
		// Slow to read so we cache it.

		DefaultListableBeanFactory grandParent = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(grandParent).loadBeanDefinitions(ROOT_CONTEXT);
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory(grandParent);
		new XmlBeanDefinitionReader(parent).loadBeanDefinitions(MIDDLE_CONTEXT);
		DefaultListableBeanFactory child = new DefaultListableBeanFactory(parent);
		new XmlBeanDefinitionReader(child).loadBeanDefinitions(LEAF_CONTEXT);

		this.dependentBeansFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(this.dependentBeansFactory).loadBeanDefinitions(DEPENDENT_BEANS_CONTEXT);
		dependentBeansFactory.preInstantiateSingletons();
		this.listableBeanFactory = child;
	}
