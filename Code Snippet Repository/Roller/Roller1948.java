    public String getTagsAsString() {
        StringBuilder sb = new StringBuilder();
        for (MediaFileTag tag : getTags()) {
            sb.append(tag.getName()).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
