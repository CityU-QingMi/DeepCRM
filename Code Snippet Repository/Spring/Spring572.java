	protected BeanWiringInfo buildWiringInfo(Object beanInstance, Configurable annotation) {
		if (!Autowire.NO.equals(annotation.autowire())) {
			return new BeanWiringInfo(annotation.autowire().value(), annotation.dependencyCheck());
		}
		else {
			if (!"".equals(annotation.value())) {
				// explicitly specified bean name
				return new BeanWiringInfo(annotation.value(), false);
			}
			else {
				// default bean name
				return new BeanWiringInfo(getDefaultBeanName(beanInstance), true);
			}
		}
	}
