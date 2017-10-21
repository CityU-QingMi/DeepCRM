    public Node adaptNode(AdapterNode parent, String propertyName, Object value) {
        Class adapterClass = getAdapterForValue(value);
        if (adapterClass != null) {
            return constructAdapterInstance(adapterClass, parent, propertyName, value);
        }

        // If the property is a Document, "unwrap" it to the root element
        if (value instanceof Document) {
            value = ((Document) value).getDocumentElement();
        }

        // If the property is already a Node, proxy it
        if (value instanceof Node) {
            return proxyNode(parent, (Node) value);
        }

        // Check other supported types or default to generic JavaBean introspecting adapter
        Class valueType = value.getClass();

        if (valueType.isArray()) {
            adapterClass = ArrayAdapter.class;
        } else if (value instanceof String || value instanceof Number || value instanceof Boolean || valueType.isPrimitive()) {
            adapterClass = StringAdapter.class;
        } else if (value instanceof Collection) {
            adapterClass = CollectionAdapter.class;
        } else if (value instanceof Map) {
            adapterClass = MapAdapter.class;
        } else {
            adapterClass = BeanAdapter.class;
        }

        return constructAdapterInstance(adapterClass, parent, propertyName, value);
    }
