	private void logln(String... message) {
		if (noisyTests) {
			if (message.length > 0) {
				System.out.println(message[0]);
			}
			else {
				System.out.println();
			}
		}
	}
