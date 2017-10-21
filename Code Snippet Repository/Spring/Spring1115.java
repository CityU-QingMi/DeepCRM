	private boolean hasIndexedReadMethodForProperty(BeanInfo beanInfo, String propertyName) {
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			if (pd.getName().equals(propertyName)) {
				if (!(pd instanceof IndexedPropertyDescriptor)) {
					return false;
				}
				return ((IndexedPropertyDescriptor)pd).getIndexedReadMethod() != null;
			}
		}
		return false;
	}
