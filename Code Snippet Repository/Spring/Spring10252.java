	@Parameterized.Parameters(name = "")
	public static Object[][] arguments() {
		File base = new File(System.getProperty("java.io.tmpdir"));
		return new Object[][] {
				{new JettyHttpServer()},
				{new RxNettyHttpServer()},
				{new ReactorHttpServer()},
				{new TomcatHttpServer(base.getAbsolutePath())},
				{new UndertowHttpServer()}
		};
	}
