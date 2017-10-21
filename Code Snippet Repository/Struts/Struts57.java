    public String start() throws BundleException {
        Bundle bundle = osgiHost.getBundles().get(id);
        try {
            bundle.start();

            //start() fires a BundleEvent.STARTED, which loads the config
            //we need to wait until the config is loaded from that bundle but
            //there no easy way/elegant way to know if the bundle was processed already
            Thread.sleep(1000);
        } catch (Exception e) {
            addActionError(e.toString());
        }

        return view();
    }
