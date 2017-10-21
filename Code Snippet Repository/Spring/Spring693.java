	protected AbstractBeanDefinition createBeanDefinition() {
		AbstractBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(this.clazz);
		if (!CollectionUtils.isEmpty(this.constructorArgs)) {
			ConstructorArgumentValues cav = new ConstructorArgumentValues();
			for (Object constructorArg : this.constructorArgs) {
				cav.addGenericArgumentValue(constructorArg);
			}
			bd.setConstructorArgumentValues(cav);
		}
		if (this.parentName != null) {
			bd.setParentName(this.parentName);
		}
		this.definitionWrapper = new BeanWrapperImpl(bd);
		return bd;
	}
