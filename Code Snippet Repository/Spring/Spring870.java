	@Override
	public String[] getBeanNamesForType(@Nullable ResolvableType type) {
		boolean isFactoryType = false;
		if (type != null) {
			Class<?> resolved = type.resolve();
			if (resolved != null && FactoryBean.class.isAssignableFrom(resolved)) {
				isFactoryType = true;
			}
		}
		List<String> matches = new ArrayList<>();
		for (Map.Entry<String, Object> entry : this.beans.entrySet()) {
			String name = entry.getKey();
			Object beanInstance = entry.getValue();
			if (beanInstance instanceof FactoryBean && !isFactoryType) {
				Class<?> objectType = ((FactoryBean<?>) beanInstance).getObjectType();
				if (objectType != null && (type == null || type.isAssignableFrom(objectType))) {
					matches.add(name);
				}
			}
			else {
				if (type == null || type.isInstance(beanInstance)) {
					matches.add(name);
				}
			}
		}
		return StringUtils.toStringArray(matches);
	}
