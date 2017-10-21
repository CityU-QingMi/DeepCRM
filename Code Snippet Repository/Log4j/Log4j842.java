    @Override
    public void purge() {
        final long createTime = System.currentTimeMillis() - timeToLive;
        for (final Entry<String, Long> entry : appendersUsage.entrySet()) {
            if (entry.getValue() < createTime) {
                LOGGER.debug("Removing appender " + entry.getKey());
                if (appendersUsage.remove(entry.getKey(), entry.getValue())) {
                    routingAppender.deleteAppender(entry.getKey());
                }
            }
        }
    }
