	@Test
	public void testAddSameMarker() {
		final String childMarkerName = CHILD_MAKER_NAME + "-ASM";
		final String parentMakerName = PARENT_MARKER_NAME + "-ASM";
		final org.slf4j.Marker slf4jMarker = org.slf4j.MarkerFactory.getMarker(childMarkerName);
		final org.slf4j.Marker slf4jParent = org.slf4j.MarkerFactory.getMarker(parentMakerName);
		slf4jMarker.add(slf4jParent);
		slf4jMarker.add(slf4jParent);
		final Marker log4jParent = MarkerManager.getMarker(parentMakerName);
		final Marker log4jMarker = MarkerManager.getMarker(childMarkerName);
		assertTrue(String.format("%s (log4jMarker=%s) is not an instance of %s (log4jParent=%s) in Log4j",
				childMarkerName, parentMakerName, log4jMarker, log4jParent), log4jMarker.isInstanceOf(log4jParent));
		assertTrue(String.format("%s (slf4jMarker=%s) is not an instance of %s (log4jParent=%s) in SLF4J",
				childMarkerName, parentMakerName, slf4jMarker, slf4jParent), slf4jMarker.contains(slf4jParent));
	}
