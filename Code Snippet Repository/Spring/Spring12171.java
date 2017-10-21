		@Override
		@Nullable
		public Object intercept(Object obj, Method method, Object[] args, @Nullable MethodProxy proxy) {
			if (method.getName().equals("getControllerMethod")) {
				return this.controllerMethod;
			}
			else if (method.getName().equals("getArgumentValues")) {
				return this.argumentValues;
			}
			else if (method.getName().equals("getControllerType")) {
				return this.controllerType;
			}
			else if (ReflectionUtils.isObjectMethod(method)) {
				return ReflectionUtils.invokeMethod(method, obj, args);
			}
			else {
				this.controllerMethod = method;
				this.argumentValues = args;
				Class<?> returnType = method.getReturnType();
				return (void.class == returnType ? null : returnType.cast(initProxy(returnType, this)));
			}
		}
