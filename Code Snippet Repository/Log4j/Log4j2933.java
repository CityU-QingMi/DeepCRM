    public Marker getMarker(final Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("Marker must not be null");
        }
        final Marker m = markerMap.get(marker.getName());
        if (m != null) {
            return m;
        }
        return addMarkerIfAbsent(marker.getName(), convertMarker(marker));
    }
