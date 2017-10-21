    private org.slf4j.Marker getMarker(final Marker marker) {
        if (marker == null) {
            return null;
        }
        final org.slf4j.Marker slf4jMarker = MarkerFactory.getMarker(marker.getName());
        final Marker[] parents = marker.getParents();
        if (parents != null) {
            for (final Marker parent : parents) {
                final org.slf4j.Marker slf4jParent = getMarker(parent);
                if (!slf4jMarker.contains(slf4jParent)) {
                    slf4jMarker.add(slf4jParent);
                }
            }
        }
        return slf4jMarker;
    }
