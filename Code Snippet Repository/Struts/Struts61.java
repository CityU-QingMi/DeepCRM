    public Collection<Bundle> getBundles() {
        List<Bundle> bundles = new ArrayList(osgiHost.getBundles().values());
        Collections.sort(bundles, new Comparator<Bundle>() {
            public int compare(Bundle bundle1, Bundle bundle2) {
                boolean bundle1StrutsEnabled = isStrutsEnabled(bundle1);
                boolean bundle2StrutsEnabled = isStrutsEnabled(bundle2);

                if ((bundle1StrutsEnabled && bundle2StrutsEnabled) || (!bundle1StrutsEnabled && !bundle2StrutsEnabled))
                    return bundle1.getSymbolicName().compareTo(bundle2.getSymbolicName());
                else {
                    return bundle1StrutsEnabled ? -1 : 1;
                }
            }
        });
        return bundles;
    }
