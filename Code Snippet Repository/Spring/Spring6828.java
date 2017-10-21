	@Override
	public ActivationSpec createActivationSpec(ResourceAdapter adapter, JmsActivationSpecConfig config) {
		Class<?> activationSpecClassToUse = this.activationSpecClass;
		if (activationSpecClassToUse == null) {
			activationSpecClassToUse = determineActivationSpecClass(adapter);
			if (activationSpecClassToUse == null) {
				throw new IllegalStateException("Property 'activationSpecClass' is required");
			}
		}

		ActivationSpec spec = (ActivationSpec) BeanUtils.instantiateClass(activationSpecClassToUse);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(spec);
		if (this.defaultProperties != null) {
			bw.setPropertyValues(this.defaultProperties);
		}
		populateActivationSpecProperties(bw, config);
		return spec;
	}
