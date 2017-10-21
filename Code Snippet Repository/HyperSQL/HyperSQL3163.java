    static void deleteRecentConnectionSettings() {

        try {
            if (recentSettings == null) {
                setHomeDir();

                if (homedir == null) {
                    return;
                }

                recentSettings = new File(homedir, fileName);
            }

            if (!recentSettings.exists()) {
                recentSettings = null;

                return;
            }

            recentSettings.delete();

            recentSettings = null;
        } catch (Throwable t) {}
    }
