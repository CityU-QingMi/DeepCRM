	private String statusOfRunnersToString(Set<UserRunner> runners) {
		assert runners != null;

		StringBuilder sb = new StringBuilder(
				"TEST CONFIG [userCount=" + USER_COUNT
						+ ", iterationsPerUser=" + ITERATION_COUNT + ", thinkTimeMillis="
						+ THINK_TIME_MILLIS + "] " + " STATE of UserRunners: "
		);

		for ( UserRunner r : runners ) {
			sb.append( r.toString() ).append( System.lineSeparator() );
		}
		return sb.toString();
	}
