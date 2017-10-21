    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[style=");
        sb.append(style);
        sb.append(", defaultStyle=");
        sb.append(defaultStyle);
        sb.append(", patternFormatters=");
        sb.append(patternFormatters);
        sb.append(", noAnsi=");
        sb.append(noAnsi);
        sb.append(']');
        return sb.toString();
    }
