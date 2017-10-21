	@Test
	public void testContainsNullString() {
		final String childMarkerName = CHILD_MAKER_NAME + "-CS";
		final String parentMakerName = PARENT_MARKER_NAME + "-CS";
		final org.slf4j.Marker slf4jMarker = org.slf4j.MarkerFactory.getMarker(childMarkerName);
		final org.slf4j.Marker slf4jParent = org.slf4j.MarkerFactory.getMarker(parentMakerName);
		slf4jMarker.add(slf4jParent);
		final Marker log4jParent = MarkerManager.getMarker(parentMakerName);
		final Marker log4jMarker = MarkerManager.getMarker(childMarkerName);
		final Log4jMarker log4jSlf4jParent = new Log4jMarker(log4jParent);
		final Log4jMarker log4jSlf4jMarker = new Log4jMarker(log4jMarker);
		final String nullStr = null;
		Assert.assertFalse(log4jSlf4jParent.contains(nullStr));
		Assert.assertFalse(log4jSlf4jMarker.contains(nullStr));
	}
