	@Override
	@Nullable
	public Object getValue(ELContext elContext, @Nullable Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			WebApplicationContext wac = getWebApplicationContext(elContext);
			if (wac.containsBean(beanName)) {
				elContext.setPropertyResolved(true);
				return wac.getBean(beanName);
			}
		}
		return null;
	}
