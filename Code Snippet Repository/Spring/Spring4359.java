		@Override
		@Nullable
		public Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
			if (method.getName().equals("equals") && args != null) {
				Object other = args[0];
				// Unwrap proxies for speed
				if (other instanceof Type) {
					other = unwrap((Type) other);
				}
				return ObjectUtils.nullSafeEquals(this.provider.getType(), other);
			}
			else if (method.getName().equals("hashCode")) {
				return ObjectUtils.nullSafeHashCode(this.provider.getType());
			}
			else if (method.getName().equals("getTypeProvider")) {
				return this.provider;
			}

			if (Type.class == method.getReturnType() && args == null) {
				return forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, -1));
			}
			else if (Type[].class == method.getReturnType() && args == null) {
				Type[] result = new Type[((Type[]) method.invoke(this.provider.getType())).length];
				for (int i = 0; i < result.length; i++) {
					result[i] = forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, i));
				}
				return result;
			}

			try {
				return method.invoke(this.provider.getType(), args);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
