	protected Class<?> createConfigInterface(BeanDefinition bd, @Nullable Class<?>[] interfaces) {
		InterfaceMaker maker = new InterfaceMaker();
		PropertyValue[] pvs = bd.getPropertyValues().getPropertyValues();
		for (PropertyValue pv : pvs) {
			String propertyName = pv.getName();
			Class<?> propertyType = BeanUtils.findPropertyType(propertyName, interfaces);
			String setterName = "set" + StringUtils.capitalize(propertyName);
			Signature signature = new Signature(setterName, Type.VOID_TYPE, new Type[] {Type.getType(propertyType)});
			maker.add(signature, new Type[0]);
		}
		if (bd instanceof AbstractBeanDefinition) {
			AbstractBeanDefinition abd = (AbstractBeanDefinition) bd;
			if (abd.getInitMethodName() != null) {
				Signature signature = new Signature(abd.getInitMethodName(), Type.VOID_TYPE, new Type[0]);
				maker.add(signature, new Type[0]);
			}
			if (StringUtils.hasText(abd.getDestroyMethodName())) {
				Signature signature = new Signature(abd.getDestroyMethodName(), Type.VOID_TYPE, new Type[0]);
				maker.add(signature, new Type[0]);
			}
		}
		return maker.create();
	}
