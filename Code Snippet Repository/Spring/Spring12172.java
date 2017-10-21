		public MethodArgumentBuilder(@Nullable UriComponentsBuilder baseUrl, Class<?> controllerType, Method method) {
			Assert.notNull(controllerType, "'controllerType' is required");
			Assert.notNull(method, "'method' is required");
			this.baseUrl = (baseUrl != null ? baseUrl : initBaseUrl());
			this.controllerType = controllerType;
			this.method = method;
			this.argumentValues = new Object[method.getParameterCount()];
			for (int i = 0; i < this.argumentValues.length; i++) {
				this.argumentValues[i] = null;
			}
		}
