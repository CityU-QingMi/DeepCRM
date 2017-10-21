	@Test
	@Ignore("")
	public void shouldBeFasterThanSynchronizedMap() throws Exception {
		Map<Integer, WeakReference<String>> synchronizedMap = Collections.synchronizedMap(new WeakHashMap<Integer, WeakReference<String>>());
		StopWatch mapTime = timeMultiThreaded("SynchronizedMap", synchronizedMap,
				new ValueFactory<WeakReference<String>>() {

					@Override
					public WeakReference<String> newValue(int v) {
						return new WeakReference<>(String.valueOf(v));
					}
				});
		System.out.println(mapTime.prettyPrint());

		this.map.setDisableTestHooks(true);
		StopWatch cacheTime = timeMultiThreaded("WeakConcurrentCache", this.map,
				new ValueFactory<String>() {

					@Override
					public String newValue(int v) {
						return String.valueOf(v);
					}
				});
		System.out.println(cacheTime.prettyPrint());

		// We should be at least 4 time faster
		assertThat(cacheTime.getTotalTimeSeconds(),
				is(lessThan(mapTime.getTotalTimeSeconds() / 4.0)));
	}
