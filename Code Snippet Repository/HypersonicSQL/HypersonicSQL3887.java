        public DBMPrefs(boolean isApplet) throws IOException {

            if (isApplet) {}
            else {
                if (homedir == null) {
                    throw new IOException(
                        "Skipping preferences since do not know home dir");
                }

                prefsFile = new File(homedir, "dbmprefs.properties");
            }

            load();
        }
