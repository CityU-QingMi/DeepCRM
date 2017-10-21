	@Override
	public boolean isReadOnly(ELContext elContext, @Nullable Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			WebApplicationContext wac = getWebApplicationContext(elContext);
			if (wac.containsBean(beanName)) {
				return true;
			}
		}
		return false;
	}
