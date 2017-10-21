	@Parameters(name = "")
	public static Object[][] repetitionData() {
		return new Object[][] {//
			{ NonAnnotatedRepeatedTestCase.class.getSimpleName(), 0, 1, 1, 1 },//
			{ DefaultRepeatValueRepeatedTestCase.class.getSimpleName(), 0, 1, 1, 1 },//
			{ NegativeRepeatValueRepeatedTestCase.class.getSimpleName(), 0, 1, 1, 1 },//
			{ RepeatedFiveTimesRepeatedTestCase.class.getSimpleName(), 0, 1, 1, 5 },//
			{ RepeatedFiveTimesViaMetaAnnotationRepeatedTestCase.class.getSimpleName(), 0, 1, 1, 5 },//
			{ TimedRepeatedTestCase.class.getSimpleName(), 3, 4, 4, (5 + 1 + 4 + 10) } //
		};
	}
