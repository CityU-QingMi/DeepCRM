    @Override
    public void bundleChanged(final BundleEvent event) {
        switch (event.getType()) {
            case BundleEvent.STARTED:
                loadProvider(event.getBundle());
                unlockIfReady();
                break;

            default:
                break;
        }
    }
