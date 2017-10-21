    public Object get(String key) {
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c > SIZE)
                return null;
            current = current.next[c];
            if (current == null)
                return null;
            if (current.value != null)
                return current.value;
        }
        return null;
    }
