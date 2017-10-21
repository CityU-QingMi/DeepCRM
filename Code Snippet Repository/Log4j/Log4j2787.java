    private NoSqlObject<W> buildMarkerEntity(final Marker marker) {
        final NoSqlObject<W> entity = this.connection.createObject();
        entity.set("name", marker.getName());

        final Marker[] parents = marker.getParents();
        if (parents != null) {
            @SuppressWarnings("unchecked")
            final NoSqlObject<W>[] parentEntities = new NoSqlObject[parents.length];
            for (int i = 0; i < parents.length; i++) {
                parentEntities[i] = buildMarkerEntity(parents[i]);
            }
            entity.set("parents", parentEntities);
        }
        return entity;
    }
