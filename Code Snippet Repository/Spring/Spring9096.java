	@Test
	@SuppressWarnings("")
	public void proxyTypeAspectJCausesRegistrationOfAnnotationTransactionAspect() {
		try {
			new AnnotationConfigApplicationContext(EnableAspectjTxConfig.class, TxManagerConfig.class);
			fail("should have thrown CNFE when trying to load AnnotationTransactionAspect. " +
					"Do you actually have org.springframework.aspects on the classpath?");
		}
		catch (Exception ex) {
			assertThat(ex.getMessage(), containsString("AspectJTransactionManagementConfiguration"));
		}
	}
