        @PerformanceSensitive("")
        private static boolean contains(final Marker parent, final Marker... localParents) {
            // performance tests showed a normal for loop is slightly faster than a for-each loop on some platforms
            // noinspection ForLoopReplaceableByForEach
            for (int i = 0, localParentsLength = localParents.length; i < localParentsLength; i++) {
                final Marker marker = localParents[i];
                if (marker == parent) {
                    return true;
                }
            }
            return false;
        }
