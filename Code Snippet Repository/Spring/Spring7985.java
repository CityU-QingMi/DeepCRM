		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if ("isOpen".equals(method.getName())) {
				return true;
			}
			if ("close".equals(method.getName())) {
				closed = true;
				return null;
			}
			if ("toString".equals(method.getName())) {
				return "";
			}
			throw new IllegalStateException();
		}
