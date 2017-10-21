	private void lazyLoadJavaMethod() {
		lazyLoadJavaClass();

		if (this.javaMethod == null) {
			if (StringUtils.isNotBlank(this.methodParameterTypes)) {
				this.javaMethod = ReflectionUtils.findMethod(this.javaClass, this.methodName,
					this.methodParameterTypes).orElseThrow(() -> new PreconditionViolationException(
						String.format("Could not find method with name [%s] and parameter types [%s] in class [%s].",
							this.methodName, this.methodParameterTypes, this.javaClass.getName())));
			}
			else {
				this.javaMethod = ReflectionUtils.findMethod(this.javaClass, this.methodName).orElseThrow(
					() -> new PreconditionViolationException(
						String.format("Could not find method with name [%s] in class [%s].", this.methodName,
							this.javaClass.getName())));
			}
		}
	}
