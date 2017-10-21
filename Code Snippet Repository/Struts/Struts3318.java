    public int compareTo(Object o) {
        if (!(o instanceof SMDMethod))
            return 1;
        if (o == null)
            return 1;
        SMDMethod other = (SMDMethod) o;
        if ((name == null) && (other.name == null))
            return 0;
        if (name == null)
            return -1;
        if (name.equals(other.name))
            return parameters.size() - other.parameters.size();

        return name.compareTo(other.name);
    }
