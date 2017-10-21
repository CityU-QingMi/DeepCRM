	@Test
	public void testCreateException() throws Exception {
		final String jndiName = "foo";

		final CreateException cex = new CreateException();
		final MyHome home = mock(MyHome.class);
		given(home.create()).willThrow(cex);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			public Object lookup(String name) {
				// parameterize
				assertTrue(name.equals(jndiName));
				return home;
			}
		};

		SimpleRemoteStatelessSessionProxyFactoryBean fb = new SimpleRemoteStatelessSessionProxyFactoryBean();
		fb.setJndiName(jndiName);
		// rely on default setting of resourceRef=false, no auto addition of java:/comp/env prefix
		fb.setBusinessInterface(MyBusinessMethods.class);
		assertEquals(fb.getBusinessInterface(), MyBusinessMethods.class);
		fb.setJndiTemplate(jt);

		// Need lifecycle methods
		fb.afterPropertiesSet();

		MyBusinessMethods mbm = (MyBusinessMethods) fb.getObject();
		assertTrue(Proxy.isProxyClass(mbm.getClass()));

		try {
			mbm.getValue();
			fail("Should have failed to create EJB");
		}
		catch (RemoteException ex) {
			// expected
		}
	}
