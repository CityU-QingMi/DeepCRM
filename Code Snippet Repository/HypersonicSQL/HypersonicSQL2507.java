    public boolean load() {

        boolean exists;

        if (!database.getType().isFileBased()) {
            return true;
        }

        try {
            exists = super.load();
        } catch (Throwable t) {
            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_LOAD_SAVE_PROPERTIES, new Object[] {
                t.toString(), fileName
            });
        }

        if (!exists) {
            return false;
        }

        filterLoadedProperties();

        String version = getStringProperty(hsqldb_version);
        int    check = version.substring(0, 5).compareTo(VERSION_STRING_1_8_0);

        // do not open early version databases
        if (check < 0) {
            throw Error.error(ErrorCode.WRONG_DATABASE_FILE_VERSION);
        }

        // do not open databases of 1.8 versions if script format is not compatible
        if (check == 0) {
            if (getIntegerProperty(hsqldb_script_format) != 0) {
                throw Error.error(ErrorCode.WRONG_DATABASE_FILE_VERSION);
            }
        }

        check = version.substring(0, 2).compareTo(THIS_VERSION);

        // do not open if the database belongs to a later (future) version (3.x)
        if (check > 0) {
            throw Error.error(ErrorCode.WRONG_DATABASE_FILE_VERSION);
        }

        return true;
    }
