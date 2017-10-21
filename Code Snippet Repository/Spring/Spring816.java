	@Override
	public <T> T getBean(Class<T> requiredType, @Nullable Object... args) throws BeansException {
		NamedBeanHolder<T> namedBean = resolveNamedBean(requiredType, args);
		if (namedBean != null) {
			return namedBean.getBeanInstance();
		}
		BeanFactory parent = getParentBeanFactory();
		if (parent != null) {
			return (args != null ? parent.getBean(requiredType, args) : parent.getBean(requiredType));
		}
		throw new NoSuchBeanDefinitionException(requiredType);
	}
