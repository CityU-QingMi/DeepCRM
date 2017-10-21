	@Override
	public void printTo(PrintWriter writer) {
		// @formatter:off
		writer.println(String.format(
			"%nTest run finished after %d ms%n"

			+ "[%10d containers found      ]%n"
			+ "[%10d containers skipped    ]%n"
			+ "[%10d containers started    ]%n"
			+ "[%10d containers aborted    ]%n"
			+ "[%10d containers successful ]%n"
			+ "[%10d containers failed     ]%n"

			+ "[%10d tests found           ]%n"
			+ "[%10d tests skipped         ]%n"
			+ "[%10d tests started         ]%n"
			+ "[%10d tests aborted         ]%n"
			+ "[%10d tests successful      ]%n"
			+ "[%10d tests failed          ]%n",

			(this.timeFinished - this.timeStarted),

			getContainersFoundCount(),
			getContainersSkippedCount(),
			getContainersStartedCount(),
			getContainersAbortedCount(),
			getContainersSucceededCount(),
			getContainersFailedCount(),

			getTestsFoundCount(),
			getTestsSkippedCount(),
			getTestsStartedCount(),
			getTestsAbortedCount(),
			getTestsSucceededCount(),
			getTestsFailedCount()
		));
		// @formatter:on

		writer.flush();
	}
