    public void save() {

        if (!database.getType().isFileBased() || database.isFilesReadOnly()
                || database.isFilesInJar()) {
            return;
        }

        try {
            HsqlProperties props = new HsqlProperties(dbMeta,
                database.getPath(), database.logger.getFileAccess(), false);

            if (getIntegerProperty(hsqldb_script_format) == 3) {
                props.setProperty(hsqldb_script_format, 3);
            }

            props.setProperty(hsqldb_version, THIS_VERSION);
            props.setProperty(
                tx_timestamp,
                Long.toString(database.logger.getFilesTimestamp()));

            if (database.logger.isStoredFileAccess()) {
                if (!database.logger.isNewStoredFileAccess()) {

// when jar is used with embedded databases in AOO 3.4 and recent(2012) LO this
// line can be uncommented to circumvent hard-coded check in OOo code in
// drivers/hsqldb/HDriver.cxx
//                    props.setProperty(hsqldb_version, VERSION_STRING_1_8_0);
                }
            }

            props.setProperty(hsqldb_modified, getProperty(hsqldb_modified));
            props.save(fileName + ".properties" + ".new");
            fa.renameElement(fileName + ".properties" + ".new",
                             fileName + ".properties");
        } catch (Throwable t) {
            database.logger.logSevereEvent("save failed", t);

            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_LOAD_SAVE_PROPERTIES, new Object[] {
                t.toString(), fileName
            });
        }
    }
