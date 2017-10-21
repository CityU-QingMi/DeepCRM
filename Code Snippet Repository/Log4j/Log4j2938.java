	@Test
	public void testEquals() {
		final Marker markerA = MarkerManager.getMarker(Log4jMarkerTest.class.getName() + "-A");
		final Marker markerB = MarkerManager.getMarker(Log4jMarkerTest.class.getName() + "-B");
		final Log4jMarker marker1 = new Log4jMarker(markerA);
		final Log4jMarker marker2 = new Log4jMarker(markerA);
		final Log4jMarker marker3 = new Log4jMarker(markerB);
		Assert.assertEquals(marker1, marker2);
		Assert.assertNotEquals(marker1, null);
		Assert.assertNotEquals(null, marker1);
		Assert.assertNotEquals(marker1, marker3);
	}
