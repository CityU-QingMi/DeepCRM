        @Override
        public boolean isEnabled(final Level level, final Marker marker, final Message data, final Throwable t) {
            assertTrue("Incorrect Level. Expected " + currentLevel + ", actual " + level, level.equals(currentLevel));
            if (marker == null) {
                if (currentEvent.markerName != null) {
                    fail("Incorrect marker. Expected " + currentEvent.markerName + ", actual is null");
                }
            } else {
                if (currentEvent.markerName == null) {
                    fail("Incorrect marker. Expected null. Actual is " + marker.getName());
                } else {
                    assertTrue("Incorrect marker. Expected " + currentEvent.markerName + ", actual " +
                            marker.getName(), currentEvent.markerName.equals(marker.getName()));
                }
            }
            if (data == null) {
                if (currentEvent.data != null) {
                    fail("Incorrect message. Expected " + currentEvent.data + ", actual is null");
                }
            } else {
                if (currentEvent.data == null) {
                    fail("Incorrect message. Expected null. Actual is " + data.getFormattedMessage());
                } else {
                    assertTrue("Incorrect message type. Expected " + currentEvent.data + ", actual " + data,
                            data.getClass().isAssignableFrom(currentEvent.data.getClass()));
                    assertTrue("Incorrect message. Expected " + currentEvent.data.getFormattedMessage() + ", actual " +
                                    data.getFormattedMessage(),
                            currentEvent.data.getFormattedMessage().equals(data.getFormattedMessage()));
                }
            }
            if (t == null) {
                if (currentEvent.t != null) {
                    fail("Incorrect Throwable. Expected " + currentEvent.t + ", actual is null");
                }
            } else {
                if (currentEvent.t == null) {
                    fail("Incorrect Throwable. Expected null. Actual is " + t);
                } else {
                    assertTrue("Incorrect Throwable. Expected " + currentEvent.t + ", actual " + t,
                            currentEvent.t.equals(t));
                }
            }
            return true;
        }
