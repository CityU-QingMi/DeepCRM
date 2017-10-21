	@Test
	public void subclassWithCustomIdAndNoTimestamp() {
		final AtomicLong id = new AtomicLong();
		@SuppressWarnings("serial")
		class MyMH extends MessageHeaders {
			public MyMH() {
				super(null, new UUID(0, id.incrementAndGet()), -1L);
			}
		}
		MessageHeaders headers = new MyMH();
		assertEquals("00000000-0000-0000-0000-000000000001", headers.getId().toString());
		assertEquals(1, headers.size());
	}
