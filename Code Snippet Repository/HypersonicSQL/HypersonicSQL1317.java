    void setupBundles() {

        Locale oldLocale;

        synchronized (ResourceBundleHandler.class) {
            oldLocale = ResourceBundleHandler.getLocale();

            ResourceBundleHandler.setLocale(Locale.getDefault());

            hnd_column_remarks =
                ResourceBundleHandler.getBundleHandle("info-column-remarks",
                    null);
            hnd_table_remarks =
                ResourceBundleHandler.getBundleHandle("info-table-remarks",
                    null);

            ResourceBundleHandler.setLocale(oldLocale);
        }
    }
