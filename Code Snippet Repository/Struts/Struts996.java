    protected List<Node> buildChildAdapters() {
        Collection values = (Collection) getPropertyValue();
        List<Node> children = new ArrayList<>(values.size());

        for (Object value : values) {
            Node childAdapter;
            if (value == null) {
                childAdapter = getAdapterFactory().adaptNullValue(this, "item");
            } else {
                childAdapter = getAdapterFactory().adaptNode(this, "item", value);
            }
            if (childAdapter != null)
                children.add(childAdapter);

            log.debug("{} adding adapter: {}", this, childAdapter);
        }

        return children;
    }
