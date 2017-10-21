    public static String getString(int handle, String key) {

        ResourceBundle bundle;
        String         s;

        synchronized (mutex) {
            if (handle < 0 || handle >= bundleList.size() || key == null) {
                bundle = null;
            } else {
                bundle = (ResourceBundle) bundleList.get(handle);
            }
        }

        if (bundle == null) {
            s = null;
        } else {
            try {
                s = bundle.getString(key);
            } catch (Exception e) {
                s = null;
            }
        }

        return s;
    }
