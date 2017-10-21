    @Override
    public int hashCode() {
        int hashCode;
        hashCode = ((name != null) ? name.hashCode() : 0);
        hashCode = (29 * hashCode) + ((exceptionClassName != null) ? exceptionClassName.hashCode() : 0);
        hashCode = (29 * hashCode) + ((result != null) ? result.hashCode() : 0);
        hashCode = (29 * hashCode) + ((params != null) ? params.hashCode() : 0);

        return hashCode;
    }
