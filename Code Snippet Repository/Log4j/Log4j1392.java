    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[leftAlign=");
        sb.append(leftAlign);
        sb.append(", maxLength=");
        sb.append(maxLength);
        sb.append(", minLength=");
        sb.append(minLength);
        sb.append(", leftTruncate=");
        sb.append(leftTruncate);
        sb.append(']');
        return sb.toString();
    }
