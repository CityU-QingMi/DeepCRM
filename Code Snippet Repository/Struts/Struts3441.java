    private void installBundles() throws Exception {
        ArrayList<Bundle> installed = new ArrayList<Bundle>();
        for (URL url : findBundles()) {
            LOG.debug("Installing bundle [" + url + "]");
            Bundle bundle = bctx.installBundle(url.toExternalForm());
            installed.add(bundle);
        }
        for (Bundle bundle : installed) {
            try {
                bundle.start();
            } catch (BundleException e) {
                e.printStackTrace();
                LOG.error("Failed to start " + bundle, e);
            }
        }

    }
