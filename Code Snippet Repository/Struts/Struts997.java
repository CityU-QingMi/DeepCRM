    protected List<Node> buildChildAdapters() {
        List<Node> children = new ArrayList<>(map().entrySet().size());

        for (Object o : map().entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object value = entry.getValue();
            EntryElement child = new EntryElement(getAdapterFactory(), this, "entry", key, value);
            children.add(child);
        }

        return children;
    }
