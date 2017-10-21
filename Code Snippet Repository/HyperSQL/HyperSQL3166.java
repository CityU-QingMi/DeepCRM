    public Vector loadRecentConnectionSettings() {

        Vector passSettings = new Vector();

        settings = new Hashtable();

        try {
            settings = ConnectionDialogCommon.loadRecentConnectionSettings();

            Iterator it = settings.values().iterator();

            passSettings.add(ConnectionDialogCommon.emptySettingName);

            while (it.hasNext()) {
                passSettings.add(((ConnectionSetting) it.next()).getName());
            }
        } catch (java.io.IOException ioe) {
            CommonSwing.errorMessage(ioe);
        }

        return (passSettings);
    }
