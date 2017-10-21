	@Before
	public void setUp() throws Exception {

		final ProtectionDomain empty = new ProtectionDomain(null,
				new Permissions());

		provider = new SecurityContextProvider() {
			private final AccessControlContext acc = new AccessControlContext(
					new ProtectionDomain[] { empty });

			@Override
			public AccessControlContext getAccessControlContext() {
				return acc;
			}
		};

		DefaultResourceLoader drl = new DefaultResourceLoader();
		Resource config = drl
				.getResource("/org/springframework/beans/factory/support/security/callbacks.xml");
		beanFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(config);
		beanFactory.setSecurityContextProvider(provider);
	}
