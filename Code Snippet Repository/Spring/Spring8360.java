		@Override
		public Validator mvcValidator() {
			Validator mvcValidator = (validator != null) ? validator : super.mvcValidator();
			if (mvcValidator instanceof InitializingBean) {
				try {
					((InitializingBean) mvcValidator).afterPropertiesSet();
				}
				catch (Exception ex) {
					throw new BeanInitializationException("Failed to initialize Validator", ex);
				}
			}
			return mvcValidator;
		}
