        @Override
        public synchronized Marker addParents(final Marker... parentMarkers) {
            requireNonNull(parentMarkers, "A parent marker must be specified");
            // It is not strictly necessary to copy the variable here but it should perform better than
            // Accessing a volatile variable multiple times.
            final Marker[] localParents = this.parents;
            // Don't add a parent that is already in the hierarchy.
            int count = 0;
            int size = parentMarkers.length;
            if (localParents != null) {
                for (final Marker parent : parentMarkers) {
                    if (!(contains(parent, localParents) || parent.isInstanceOf(this))) {
                        ++count;
                    }
                }
                if (count == 0) {
                    return this;
                }
                size = localParents.length + count;
            }
            final Marker[] markers = new Marker[size];
            if (localParents != null) {
                // It's perfectly OK to call arraycopy in a synchronized context; it's still faster
                // noinspection CallToNativeMethodWhileLocked
                System.arraycopy(localParents, 0, markers, 0, localParents.length);
            }
            int index = localParents == null ? 0 : localParents.length;
            for (final Marker parent : parentMarkers) {
                if (localParents == null || !(contains(parent, localParents) || parent.isInstanceOf(this))) {
                    markers[index++] = parent;
                }
            }
            this.parents = markers;
            return this;
        }
