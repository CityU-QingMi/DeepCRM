    public String upgrade() {

        if (WebloggerFactory.isBootstrapped()) {
            return SUCCESS;
        }

        try {
            messages = WebloggerStartup.upgradeDatabase(true);

            success = true;
        } catch (StartupException se) {
            error = true;
            messages = se.getStartupLog();
        }

        setPageTitle("installer.database.upgrade.pageTitle");
        return UPGRADE_DATABASE;
    }
