	@Override
	public void afterPropertiesSet() throws JiBXException {
		if (this.targetClass != null) {
			if (StringUtils.hasLength(this.bindingName)) {
				if (logger.isInfoEnabled()) {
					logger.info("Configured for target class [" + this.targetClass + "] using binding [" + this.bindingName + "]");
				}
				this.bindingFactory = BindingDirectory.getFactory(this.bindingName, this.targetClass);
			}
			else {
				if (logger.isInfoEnabled()) {
					logger.info("Configured for target class [" + this.targetClass + "]");
				}
				this.bindingFactory = BindingDirectory.getFactory(this.targetClass);
			}
		}
		else if (this.targetPackage != null) {
			if (!StringUtils.hasLength(bindingName)) {
				bindingName = DEFAULT_BINDING_NAME;
			}
			if (logger.isInfoEnabled()) {
				logger.info("Configured for target package [" + this.targetPackage	+ "] using binding [" + this.bindingName + "]");
			}
			this.bindingFactory = BindingDirectory.getFactory(this.bindingName, this.targetPackage);
		}
		else {
			throw new IllegalArgumentException("Either 'targetClass' or 'targetPackage' is required");
		}
	}
