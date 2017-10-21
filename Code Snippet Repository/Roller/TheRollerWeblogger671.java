    public String create() {

        if (WebloggerFactory.isBootstrapped()) {
            return SUCCESS;
        }

        try {
            messages = WebloggerStartup.createDatabase();

            success = true;
        } catch (StartupException se) {
            error = true;
            messages = se.getStartupLog();
        }

        setPageTitle("installer.database.creation.pageTitle");
        return CREATE_DATABASE;
    }
