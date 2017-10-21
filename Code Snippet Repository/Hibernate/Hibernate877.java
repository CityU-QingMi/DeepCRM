	public static Class reflectedPropertyClass(
			MetadataBuildingContext buildingContext,
			Class attributeOwnerClass,
			final String attributeName) {
//		return BeanInfoHelper.visitBeanInfo(
//				attributeOwnerClass,
//				new BeanInfoHelper.ReturningBeanInfoDelegate<Class>() {
//					@Override
//					public Class processBeanInfo(BeanInfo beanInfo) throws Exception {
//						for ( PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors() ) {
//							if ( propertyDescriptor.getName().equals( attributeName ) ) {
//								return propertyDescriptor.getPropertyType();
//							}
//						}
//						return null;
//					}
//				}
//		);
		return ReflectHelper.reflectedPropertyClass( attributeOwnerClass, attributeName );
	}
