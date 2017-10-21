	@Test
	public void getMultipleInstances() throws Exception {
		final TestBean[] beans = new TestBean[2];
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				beans[0] = applicationContext.getBean("threadScopedObject", TestBean.class);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				beans[1] = applicationContext.getBean("threadScopedObject", TestBean.class);
			}
		});
		thread1.start();
		thread2.start();

		Thread.sleep(200);

		assertNotNull(beans[0]);
		assertNotNull(beans[1]);
		assertNotSame(beans[0], beans[1]);
	}
