		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) {
			if (ReflectionUtils.isObjectMethod(method)) {
				return ReflectionUtils.invokeMethod(method, object, args);
			}
			else {
				this.invokedMethod = method;
				return null;
			}
		}
