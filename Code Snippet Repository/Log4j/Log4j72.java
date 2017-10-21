        @Override
        @PerformanceSensitive({"", ""})
        public boolean isInstanceOf(final String markerName) {
            requireNonNull(markerName, "A marker name is required");
            if (markerName.equals(this.getName())) {
                return true;
            }
            // Use a real marker for child comparisons. It is faster than comparing the names.
            final Marker marker = MARKERS.get(markerName);
            if (marker == null) {
                return false;
            }
            final Marker[] localParents = parents;
            if (localParents != null) {
                final int localParentsLength = localParents.length;
                if (localParentsLength == 1) {
                    return checkParent(localParents[0], marker);
                }
                if (localParentsLength == 2) {
                    return checkParent(localParents[0], marker) || checkParent(localParents[1], marker);
                }
                // noinspection ForLoopReplaceableByForEach
                for (int i = 0; i < localParentsLength; i++) {
                    final Marker localParent = localParents[i];
                    if (checkParent(localParent, marker)) {
                        return true;
                    }
                }
            }

            return false;
        }
