        @PerformanceSensitive({"", ""})
        private static boolean checkParent(final Marker parent, final Marker marker) {
            if (parent == marker) {
                return true;
            }
            final Marker[] localParents = parent instanceof Log4jMarker ? ((Log4jMarker) parent).parents : parent
                    .getParents();
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
