	@Override
	public void setValue(ELContext elContext, @Nullable Object base, Object property, Object value) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			WebApplicationContext wac = getWebApplicationContext(elContext);
			if (wac.containsBean(beanName)) {
				if (value == wac.getBean(beanName)) {
					// Setting the bean reference to the same value is alright - can simply be ignored...
					elContext.setPropertyResolved(true);
				}
				else {
					throw new PropertyNotWritableException(
							"Variable '" + beanName + "' refers to a Spring bean which by definition is not writable");
				}
			}
		}
	}
