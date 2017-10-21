    protected List<Node> buildChildAdapters() {
        List<Node> children = new ArrayList<>();
        Object[] values = (Object[]) getPropertyValue();

        for (Object value : values) {
            Node childAdapter = getAdapterFactory().adaptNode(this, "item", value);
            if (childAdapter != null)
                children.add(childAdapter);

            log.debug("{} adding adapter: {}", this, childAdapter);
        }

        return children;
    }
