	@Test
	public void isExpired() throws InterruptedException {
		assertFalse(new FlashMap().isExpired());

		FlashMap flashMap = new FlashMap();
		flashMap.startExpirationPeriod(0);
		Thread.sleep(100);

		assertTrue(flashMap.isExpired());
	}
