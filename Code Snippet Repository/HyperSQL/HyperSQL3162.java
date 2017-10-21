    private static void storeRecentConnectionSettings(Hashtable settings) {

        try {
            if (recentSettings == null) {
                setHomeDir();

                if (homedir == null) {
                    return;
                }

                recentSettings = new File(homedir, fileName);

                if (!recentSettings.exists()) {

//                    recentSettings.createNewFile();
                }
            }

            if (settings == null || settings.size() == 0) {
                return;
            }

            // setup a stream to a physical file on the filesystem
            FileOutputStream   out = new FileOutputStream(recentSettings);
            ObjectOutputStream objStream = new ObjectOutputStream(out);
            Enumeration        en        = settings.elements();

            while (en.hasMoreElements()) {
                objStream.writeObject(en.nextElement());
            }

            objStream.flush();
            objStream.close();
            out.close();
        } catch (Throwable t) {}
    }
