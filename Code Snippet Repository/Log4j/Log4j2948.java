	@Test
	public void testRemoveNullMarker() {
		final String childMakerName = CHILD_MAKER_NAME + "-CM";
		final String parentMakerName = PARENT_MARKER_NAME + "-CM";
		final org.slf4j.Marker slf4jMarker = org.slf4j.MarkerFactory.getMarker(childMakerName);
		final org.slf4j.Marker slf4jParent = org.slf4j.MarkerFactory.getMarker(parentMakerName);
		slf4jMarker.add(slf4jParent);
		final Marker log4jParent = MarkerManager.getMarker(parentMakerName);
		final Marker log4jMarker = MarkerManager.getMarker(childMakerName);
		final Log4jMarker log4jSlf4jParent = new Log4jMarker(log4jParent);
		final Log4jMarker log4jSlf4jMarker = new Log4jMarker(log4jMarker);
		final org.slf4j.Marker nullMarker = null;
		Assert.assertFalse(log4jSlf4jParent.remove(nullMarker));
		Assert.assertFalse(log4jSlf4jMarker.remove(nullMarker));
	}
