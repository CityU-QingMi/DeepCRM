    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[converter=");
        sb.append(converter);
        sb.append(", field=");
        sb.append(field);
        sb.append(']');
        return sb.toString();
    }
