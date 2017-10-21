	public static void assumingThat(boolean assumption, Executable executable) {
		if (assumption) {
			try {
				executable.execute();
			}
			catch (Throwable t) {
				ExceptionUtils.throwAsUncheckedException(t);
			}
		}
	}
