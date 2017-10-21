	@Override
	@Test
	public void testRemoteException() throws Exception {
		final RemoteException rex = new RemoteException();
		final String jndiName = "foo";

		MyEjb myEjb = mock(MyEjb.class);
		given(myEjb.getValue()).willThrow(rex);
		// TODO might want to control this behaviour...
		// Do we really want to call remove after a remote exception?

		final MyHome home = mock(MyHome.class);
		given(home.create()).willReturn(myEjb);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			public Object lookup(String name) {
				// parameterize
				assertTrue(name.equals("java:comp/env/" + jndiName));
				return home;
			}
		};

		SimpleRemoteStatelessSessionProxyFactoryBean fb = new SimpleRemoteStatelessSessionProxyFactoryBean();
		fb.setJndiName(jndiName);
		fb.setResourceRef(true);
		fb.setBusinessInterface(MyBusinessMethods.class);
		fb.setJndiTemplate(jt);

		// Need lifecycle methods
		fb.afterPropertiesSet();

		MyBusinessMethods mbm = (MyBusinessMethods) fb.getObject();
		assertTrue(Proxy.isProxyClass(mbm.getClass()));
		try {
			mbm.getValue();
			fail("Should've thrown remote exception");
		}
		catch (RemoteException ex) {
			assertSame("Threw expected RemoteException", rex, ex);
		}
		verify(myEjb).remove();
	}
