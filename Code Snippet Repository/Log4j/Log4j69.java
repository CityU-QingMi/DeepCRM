        @Override
        public synchronized boolean remove(final Marker parent) {
            requireNonNull(parent, "A parent marker must be specified");
            final Marker[] localParents = this.parents;
            if (localParents == null) {
                return false;
            }
            final int localParentsLength = localParents.length;
            if (localParentsLength == 1) {
                if (localParents[0].equals(parent)) {
                    parents = null;
                    return true;
                }
                return false;
            }
            int index = 0;
            final Marker[] markers = new Marker[localParentsLength - 1];
            // noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < localParentsLength; i++) {
                final Marker marker = localParents[i];
                if (!marker.equals(parent)) {
                    if (index == localParentsLength - 1) {
                        // no need to swap array
                        return false;
                    }
                    markers[index++] = marker;
                }
            }
            parents = markers;
            return true;
        }
