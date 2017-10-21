	@Test
	public void retrieveAndUpdateRemoveExpired() throws InterruptedException {
		List<FlashMap> flashMaps = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			FlashMap expiredFlashMap = new FlashMap();
			expiredFlashMap.startExpirationPeriod(-1);
			flashMaps.add(expiredFlashMap);
		}
		this.flashMapManager.setFlashMaps(flashMaps);
		this.flashMapManager.retrieveAndUpdate(this.request, this.response);

		assertEquals("Expired instances should be removed even if the saved FlashMap is empty",
				0, this.flashMapManager.getFlashMaps().size());
	}
