	@Override
	protected void populateAttributeDescriptor(
			Descriptor desc, @Nullable Method getter, @Nullable Method setter, String beanKey) {

		if (getter != null) {
			ManagedMetric metric = obtainAttributeSource().getManagedMetric(getter);
			if (metric != null) {
				populateMetricDescriptor(desc, metric);
				return;
			}
		}

		ManagedAttribute gma = (getter != null ? obtainAttributeSource().getManagedAttribute(getter) : null);
		ManagedAttribute sma = (setter != null ? obtainAttributeSource().getManagedAttribute(setter) : null);
		populateAttributeDescriptor(desc,
				(gma != null ? gma : ManagedAttribute.EMPTY),
				(sma != null ? sma : ManagedAttribute.EMPTY));
	}
