	public ConsoleLauncherWrapperResult execute(Optional<Integer> expectedCode, String... args) {
		ConsoleLauncherExecutionResult result = consoleLauncher.execute(args);
		int code = result.getExitCode();
		String outText = new String(out.toByteArray(), charset);
		String errText = new String(err.toByteArray(), charset);
		if (expectedCode.isPresent()) {
			int expectedValue = expectedCode.get();
			assertAll("wrapped execution failed:\n" + outText + "\n", //
				() -> assertEquals(expectedValue, code, "ConsoleLauncher execute code mismatch!"), //
				() -> assertTrue(expectedValue == 0 ? isBlank(errText) : isNotBlank(errText)) //
			);
		}
		return new ConsoleLauncherWrapperResult(args, charset, outText, errText, result);
	}
