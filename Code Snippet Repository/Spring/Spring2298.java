	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass, @Nullable Supplier<T> supplier,
			BeanDefinitionCustomizer... customizers) {

		BeanDefinitionBuilder builder = (supplier != null ?
				BeanDefinitionBuilder.genericBeanDefinition(beanClass, supplier) :
				BeanDefinitionBuilder.genericBeanDefinition(beanClass));
		BeanDefinition beanDefinition = builder.applyCustomizers(customizers).getRawBeanDefinition();

		String nameToUse = (beanName != null ? beanName : beanClass.getName());
		registerBeanDefinition(nameToUse, beanDefinition);
	}
