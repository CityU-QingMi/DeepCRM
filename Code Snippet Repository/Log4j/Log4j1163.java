    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder();
        s.append('{')
                .append(allLines() ? FULL : this.lines == 2 ? SHORT : anyLines() ? String.valueOf(this.lines) : NONE)
                .append('}');
        s.append("{separator(").append(this.separator).append(")}");
        if (hasPackages()) {
            s.append("{filters(");
            for (final String p : this.ignorePackages) {
                s.append(p).append(',');
            }
            s.deleteCharAt(s.length() - 1);
            s.append(")}");
        }
        return s.toString();
    }
