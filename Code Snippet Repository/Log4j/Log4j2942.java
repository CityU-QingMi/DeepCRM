	@Test
	public void testAddMarker() {
		final String childMakerName = CHILD_MAKER_NAME + "-AM";
		final String parentMarkerName = PARENT_MARKER_NAME + "-AM";
		final org.slf4j.Marker slf4jMarker = org.slf4j.MarkerFactory.getMarker(childMakerName);
		final org.slf4j.Marker slf4jParent = org.slf4j.MarkerFactory.getMarker(parentMarkerName);
		slf4jMarker.add(slf4jParent);
		final Marker log4jParent = MarkerManager.getMarker(parentMarkerName);
		final Marker log4jMarker = MarkerManager.getMarker(childMakerName);

		assertTrue("Incorrect Marker class", slf4jMarker instanceof Log4jMarker);
		assertTrue(String.format("%s (log4jMarker=%s) is not an instance of %s (log4jParent=%s) in Log4j",
				childMakerName, parentMarkerName, log4jMarker, log4jParent), log4jMarker.isInstanceOf(log4jParent));
		assertTrue(String.format("%s (slf4jMarker=%s) is not an instance of %s (log4jParent=%s) in SLF4J",
				childMakerName, parentMarkerName, slf4jMarker, slf4jParent), slf4jMarker.contains(slf4jParent));
	}
