		@Override
		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
			GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
			beanDefinition.setBeanClassName(String.class.getName());
			registry.registerBeanDefinition("registrarImportedBean", beanDefinition);
			GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();
			beanDefinition2.setBeanClass(OtherImportedConfig.class);
			registry.registerBeanDefinition("registrarImportedConfig", beanDefinition2);
			Assert.state(!called, "ImportedRegistrar called twice");
			called = true;
		}
