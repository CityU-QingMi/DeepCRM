    void setNewDatabaseProperties() {

        // version of a new database
        setProperty(hsqldb_version, THIS_VERSION);
        setProperty(hsqldb_modified, MODIFIED_NO_NEW);

        // OOo related code
        if (database.logger.isStoredFileAccess()) {
            setProperty(hsqldb_cache_rows, 25000);
            setProperty(hsqldb_cache_size, 6000);
            setProperty(hsqldb_log_size, 10);
            setProperty(sql_enforce_size, true);
            setProperty(hsqldb_nio_data_file, false);
            setProperty(hsqldb_lock_file, true);
            setProperty(hsqldb_default_table_type, "cached");
            setProperty(jdbc_translate_tti_types, true);
        }

        // OOo end
    }
