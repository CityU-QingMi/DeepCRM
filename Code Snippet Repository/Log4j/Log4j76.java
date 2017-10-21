        @PerformanceSensitive("")
        private static void addParentInfo(final StringBuilder sb, final Marker... parents) {
            sb.append("[ ");
            boolean first = true;
            // noinspection ForLoopReplaceableByForEach
            for (int i = 0, parentsLength = parents.length; i < parentsLength; i++) {
                final Marker marker = parents[i];
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(marker.getName());
                final Marker[] p = marker instanceof Log4jMarker ? ((Log4jMarker) marker).parents : marker.getParents();
                if (p != null) {
                    addParentInfo(sb, p);
                }
            }
            sb.append(" ]");
        }
