    private void addStructuredData(final Map<String, StructuredDataElement> sdElements, final StructuredDataMessage data) {
        final Map<String, String> map = data.getData();
        final StructuredDataId id = data.getId();
        final String sdId = getId(id);

        if (sdElements.containsKey(sdId)) {
            final StructuredDataElement union = sdElements.get(id.toString());
            union.union(map);
            sdElements.put(sdId, union);
        } else {
            final StructuredDataElement formattedData = new StructuredDataElement(map, eventPrefix, false);
            sdElements.put(sdId, formattedData);
        }
    }
