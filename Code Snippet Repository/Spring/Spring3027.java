	@Override
	public void init() {
		registerBeanDefinitionParser("testBean", new TestBeanDefinitionParser());
		registerBeanDefinitionParser("person", new PersonDefinitionParser());

		registerBeanDefinitionDecorator("set", new PropertyModifyingBeanDefinitionDecorator());
		registerBeanDefinitionDecorator("debug", new DebugBeanDefinitionDecorator());
		registerBeanDefinitionDecorator("nop", new NopInterceptorBeanDefinitionDecorator());
		registerBeanDefinitionDecoratorForAttribute("object-name", new ObjectNameBeanDefinitionDecorator());
	}
