    public static int getBundleHandle(String name, ClassLoader cl) {

        Integer        bundleHandle;
        ResourceBundle bundle;
        String         bundleName;
        String         bundleKey;

        bundleName = prefix + name;

        synchronized (mutex) {
            bundleKey    = locale.toString() + bundleName;
            bundleHandle = (Integer) bundleHandleMap.get(bundleKey);

            if (bundleHandle == null) {
                bundle = getBundle(bundleName, locale, cl);

                bundleList.add(bundle);

                bundleHandle = Integer.valueOf(bundleList.size() - 1);

                bundleHandleMap.put(bundleKey, bundleHandle);
            }
        }

        return bundleHandle.intValue();
    }
