    @Override
    protected Object getGxpInstance() {
        try {
            return objectFactory.buildBean(gxpInterface, null);
        } catch (Exception e) {
            logger.log(
                    Level.INFO, "Error instantiating {0}; trying {1}",
                    new Object[]{gxpInterface.getCanonicalName(), gxpInstance.getCanonicalName(),});
            try {
                return objectFactory.buildBean(gxpInstance, null);
            } catch (Exception e1) {
                throw new RuntimeException(String.format("Error instantiating %s",
                        gxpInterface.getCanonicalName(), gxpInstance.getCanonicalName()),
                        e1);
            }
        }
    }
