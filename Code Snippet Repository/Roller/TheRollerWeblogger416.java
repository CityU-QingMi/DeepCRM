    public void putEntryAttribute(String name, String value) throws Exception {
        WeblogEntryAttribute att = null;
        for (WeblogEntryAttribute o : getEntryAttributes()) {
            if (name.equals(o.getName())) {
                att = o; 
                break;
            }
        }
        if (att == null) {
            att = new WeblogEntryAttribute();
            att.setEntry(this);
            att.setName(name);
            att.setValue(value);
            getEntryAttributes().add(att);
        } else {
            att.setValue(value);
        }
    }
