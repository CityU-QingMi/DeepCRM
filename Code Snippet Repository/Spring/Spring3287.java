		@Bean
		public static BeanDefinitionRegistryPostProcessor bdrpp() {
			return new BeanDefinitionRegistryPostProcessor() {
				@Override
				public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
					registry.registerBeanDefinition("myTestBean", new RootBeanDefinition(TestBean.class));
				}
				@Override
				public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
				}
			};
		}
