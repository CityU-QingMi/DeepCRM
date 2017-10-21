    public String stop() throws BundleException {
        Bundle bundle = osgiHost.getBundles().get(id);
        try {
            bundle.stop();
        } catch (Exception e) {
            addActionError(e.toString());
        }

        return view();
    }
