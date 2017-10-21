	@Test
	void getOutermostInstance() {
		FirstClass firstClass = new FirstClass();
		FirstClass.SecondClass secondClass = firstClass.new SecondClass();
		FirstClass.SecondClass.ThirdClass thirdClass = secondClass.new ThirdClass();

		assertThat(ReflectionUtils.getOutermostInstance(thirdClass, FirstClass.SecondClass.ThirdClass.class))//
				.contains(thirdClass);
		assertThat(ReflectionUtils.getOutermostInstance(thirdClass, FirstClass.SecondClass.class))//
				.contains(secondClass);
		assertThat(ReflectionUtils.getOutermostInstance(thirdClass, FirstClass.class)).contains(firstClass);
		assertThat(ReflectionUtils.getOutermostInstance(thirdClass, String.class)).isEmpty();
	}
