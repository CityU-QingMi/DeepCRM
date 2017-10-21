		@Override
		public Object invoke(MethodInvocation methodInvocation) throws Throwable {
			boolean applyDeliveryCalls = !hasBeforeDeliveryBeenCalled();
			if (applyDeliveryCalls) {
				try {
					beforeDelivery(null);
				}
				catch (ResourceException ex) {
					if (ReflectionUtils.declaresException(methodInvocation.getMethod(), ex.getClass())) {
						throw ex;
					}
					else {
						throw new InternalResourceException(ex);
					}
				}
			}
			try {
				return methodInvocation.proceed();
			}
			catch (Throwable ex) {
				onEndpointException(ex);
				throw ex;
			}
			finally {
				if (applyDeliveryCalls) {
					try {
						afterDelivery();
					}
					catch (ResourceException ex) {
						if (ReflectionUtils.declaresException(methodInvocation.getMethod(), ex.getClass())) {
							throw ex;
						}
						else {
							throw new InternalResourceException(ex);
						}
					}
				}
			}
		}
