	@Override
	@Nullable
	public Class<?> getType(ELContext elContext, @Nullable Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			WebApplicationContext wac = getWebApplicationContext(elContext);
			if (wac.containsBean(beanName)) {
				elContext.setPropertyResolved(true);
				return wac.getType(beanName);
			}
		}
		return null;
	}
