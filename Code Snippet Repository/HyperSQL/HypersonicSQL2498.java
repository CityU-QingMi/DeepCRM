    public int getDBModified() {

        String value = getStringProperty(hsqldb_modified);

        if (MODIFIED_YES.equals(value)) {
            return FILES_MODIFIED;
        } else if (MODIFIED_YES_NEW.equals(value)) {
            return FILES_MODIFIED_NEW;
        } else if (MODIFIED_NO_NEW.equals(value)) {
            return FILES_NEW;
        }

        return FILES_NOT_MODIFIED;
    }
