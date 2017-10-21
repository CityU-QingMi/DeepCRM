	@BeforeEach
	@AfterEach
	void beforeAndAfterEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		if (testInfo.getTestMethod().get().getName().equals("repeatedOnce")) {
			assertThat(repetitionInfo.getCurrentRepetition()).isEqualTo(1);
			assertThat(repetitionInfo.getTotalRepetitions()).isEqualTo(1);
		}
		else if (testInfo.getTestMethod().get().getName().equals("repeatedFortyTwoTimes")) {
			assertThat(repetitionInfo.getCurrentRepetition()).isBetween(1, 42);
			assertThat(repetitionInfo.getTotalRepetitions()).isEqualTo(42);
		}
	}
