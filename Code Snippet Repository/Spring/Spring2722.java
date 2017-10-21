		private void checkAdvice(String whatJustHappened) {
			//System.out.println("[" + adviceInvocationNumber + "] " + whatJustHappened + " ==> " + EXPECTED[adviceInvocationNumber]);
			if (adviceInvocationNumber > (EXPECTED.length - 1)) {
				fail("Too many advice invocations, expecting " + EXPECTED.length
						+ " but had " + adviceInvocationNumber);
			}
			String expecting = EXPECTED[adviceInvocationNumber++];
			if (!whatJustHappened.equals(expecting)) {
				fail("Expecting '" + expecting + "' on advice invocation " + adviceInvocationNumber +
						" but got '" + whatJustHappened + "'");
			}
		}
