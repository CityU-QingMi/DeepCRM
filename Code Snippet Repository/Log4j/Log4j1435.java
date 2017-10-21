    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (!(getName().equals(filePath.toString()))) {
            sb.append("name=").append(getName()).append(", ");
        }
        sb.append("path=").append(filePath);
        if (getLanguage() != null) {
            sb.append(", language=").append(getLanguage());
        }
        sb.append(", isWatched=").append(isWatched);
        return sb.toString();
    }
