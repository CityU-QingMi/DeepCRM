	private ConsoleLauncherWrapper(Charset charset, CommandLineOptionsParser parser) {
		this.charset = charset;
		try {
			PrintStream streamOut = new PrintStream(out, false, charset.name());
			PrintStream streamErr = new PrintStream(err, false, charset.name());
			this.consoleLauncher = new ConsoleLauncher(parser, streamOut, streamErr, charset);
		}
		catch (UnsupportedEncodingException exception) {
			throw new AssertionError("Charset instance created but unsupported?!", exception);
		}
	}
