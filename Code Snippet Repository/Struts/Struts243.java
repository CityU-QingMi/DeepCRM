    private Key(Class<T> type, String name) {
        if (type == null) {
            throw new NullPointerException("Type is null.");
        }
        if (name == null) {
            throw new NullPointerException("Name is null.");
        }

        this.type = type;
        this.name = name;

        hashCode = type.hashCode() * 31 + name.hashCode();
    }
