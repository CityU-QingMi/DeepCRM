    protected void processFileItemStreamAsFormField(FileItemStream itemStream) {
        String fieldName = itemStream.getFieldName();
        try {
            List<String> values;
            String fieldValue = Streams.asString(itemStream.openStream());
            if (!parameters.containsKey(fieldName)) {
                values = new ArrayList<>();
                parameters.put(fieldName, values);
            } else {
                values = parameters.get(fieldName);
            }
            values.add(fieldValue);
        } catch (IOException e) {
            LOG.warn("Failed to handle form field '{}'.", fieldName, e);
        }
    }
