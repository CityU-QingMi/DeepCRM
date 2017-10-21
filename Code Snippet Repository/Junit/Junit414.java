	@SuppressWarnings({ "", "", "" })
	@SafeVarargs
	private final Condition<? super ExecutionEvent>[] wrappedInContainerEvents(Class<MyTestTemplateTestCase> clazz,
			Condition<? super ExecutionEvent>... wrappedConditions) {

		List<Condition<? super ExecutionEvent>> conditions = new ArrayList<>();
		conditions.add(event(engine(), started()));
		conditions.add(event(container(clazz), started()));
		conditions.addAll(asList(wrappedConditions));
		conditions.add(event(container(clazz), finishedSuccessfully()));
		conditions.add(event(engine(), finishedSuccessfully()));
		return conditions.toArray(new Condition[0]);
	}
