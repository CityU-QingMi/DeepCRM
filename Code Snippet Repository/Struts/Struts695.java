    @Override
    public String toString() {
        return "ActionMapping{" +
                "name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", method='" + method + '\'' +
                ", extension='" + extension + '\'' +
                ", params=" + params +
                ", result=" + (result != null ? result.getClass().getName() : "null") +
                '}';
    }
