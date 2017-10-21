    @SuppressWarnings("")
    public static <M extends AbstractManager, T> M getManager(final String name, final ManagerFactory<M, T> factory,
                                                              final T data) {
        LOCK.lock();
        try {
            @SuppressWarnings("unchecked")
            M manager = (M) MAP.get(name);
            if (manager == null) {
                manager = factory.createManager(name, data);
                if (manager == null) {
                    throw new IllegalStateException("ManagerFactory [" + factory + "] unable to create manager for ["
                            + name + "] with data [" + data + "]");
                }
                MAP.put(name, manager);
            } else {
                manager.updateData(data);
            }
            manager.count++;
            return manager;
        } finally {
            LOCK.unlock();
        }
    }
