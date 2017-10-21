		@RuntimeType
		public static Object intercept(
				@This final Object instance,
				@Origin final Method method,
				@AllArguments final Object[] arguments,
				@StubValue final Object stubValue,
				@FieldValue(INTERCEPTOR_FIELD_NAME) Interceptor interceptor
		) throws Throwable {
			if ( interceptor == null ) {
				if ( method.getName().equals( "getHibernateLazyInitializer" ) ) {
					return instance;
				}
				else {
					return stubValue;
				}
			}
			else {
				return interceptor.intercept( instance, method, arguments );
			}
		}
