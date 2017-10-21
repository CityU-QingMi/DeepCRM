	@Test
	public void testEquals() {
		final String childMarkerName = CHILD_MAKER_NAME + "-ASM";
		final String parentMakerName = PARENT_MARKER_NAME + "-ASM";
		final org.slf4j.Marker slf4jMarker = org.slf4j.MarkerFactory.getMarker(childMarkerName);
		final org.slf4j.Marker slf4jMarker2 = org.slf4j.MarkerFactory.getMarker(childMarkerName);
		final org.slf4j.Marker slf4jParent = org.slf4j.MarkerFactory.getMarker(parentMakerName);
		slf4jMarker.add(slf4jParent);
		final Marker log4jParent = MarkerManager.getMarker(parentMakerName);
		final Marker log4jMarker = MarkerManager.getMarker(childMarkerName);
		final Marker log4jMarker2 = MarkerManager.getMarker(childMarkerName);
		assertEquals(log4jParent, log4jParent);
		assertEquals(log4jMarker, log4jMarker);
		assertEquals(log4jMarker, log4jMarker2);
		assertEquals(slf4jMarker, slf4jMarker2);
		assertNotEquals(log4jParent, log4jMarker);
		assertNotEquals(log4jMarker, log4jParent);
	}
