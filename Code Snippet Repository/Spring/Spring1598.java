	@Test
	public void testSecuritySanity() throws Exception {
		AccessControlContext acc = provider.getAccessControlContext();
		try {
			acc.checkPermission(new PropertyPermission("*", "read"));
			fail("Acc should not have any permissions");
		}
		catch (SecurityException se) {
			// expected
		}

		final CustomCallbackBean bean = new CustomCallbackBean();
		final Method method = bean.getClass().getMethod("destroy");
		method.setAccessible(true);

		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {

				@Override
				public Object run() throws Exception {
					method.invoke(bean);
					return null;
				}
			}, acc);
			fail("expected security exception");
		}
		catch (Exception ex) {
		}

		final Class<ConstructorBean> cl = ConstructorBean.class;
		try {
			AccessController.doPrivileged(
					new PrivilegedExceptionAction<Object>() {

						@Override
						public Object run() throws Exception {
							return cl.newInstance();
						}
					}, acc);
			fail("expected security exception");
		}
		catch (Exception ex) {
		}
	}
