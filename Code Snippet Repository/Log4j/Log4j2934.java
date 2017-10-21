    private static org.apache.logging.log4j.Marker convertMarker(final Marker original,
                                                                 final Collection<Marker> visited) {
        final org.apache.logging.log4j.Marker marker = MarkerManager.getMarker(original.getName());
        if (original.hasReferences()) {
            final Iterator<Marker> it = original.iterator();
            while (it.hasNext()) {
                final Marker next = it.next();
                if (visited.contains(next)) {
                    LOGGER.warn("Found a cycle in Marker [{}]. Cycle will be broken.", next.getName());
                } else {
                    visited.add(next);
                    marker.addParents(convertMarker(next, visited));
                }
            }
        }
        return marker;
    }
