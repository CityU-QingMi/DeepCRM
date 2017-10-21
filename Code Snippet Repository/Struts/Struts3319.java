    public int compareTo(Object o) {
        if (!(o instanceof SMDMethodParameter))
            return 1;
        if (o == null)
            return 1;
        if ((name == null) && (((SMDMethodParameter) o).name == null))
            return 0;
        if (name == null)
            return -1;
        return name.compareTo(((SMDMethodParameter) o).name);
    }
