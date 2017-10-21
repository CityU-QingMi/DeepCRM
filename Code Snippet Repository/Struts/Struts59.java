    public String update() throws BundleException {
        Bundle bundle = osgiHost.getBundles().get(id);
        try {
            bundle.update();
        } catch (Exception e) {
            addActionError(e.toString());
        }

        return view();
    }
