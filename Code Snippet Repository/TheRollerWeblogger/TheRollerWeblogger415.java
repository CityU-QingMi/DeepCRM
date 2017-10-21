    public String findEntryAttribute(String name) {
        if (getEntryAttributes() != null) {
            for (WeblogEntryAttribute att : getEntryAttributes()) {
                if (name.equals(att.getName())) {
                    return att.getValue();
                }
            }
        }
        return null;
    }
