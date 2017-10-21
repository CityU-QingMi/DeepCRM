        @Override
        @PerformanceSensitive({"", ""})
        public boolean isInstanceOf(final Marker marker) {
            requireNonNull(marker, "A marker parameter is required");
            if (this == marker) {
                return true;
            }
            final Marker[] localParents = parents;
            if (localParents != null) {
                // With only one or two parents the for loop is slower.
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
