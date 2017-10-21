    protected EntryMessage entryMsg(final String format, final Supplier<?>... paramSuppliers) {
        final int count = paramSuppliers == null ? 0 : paramSuppliers.length;
        final Object[] params = new Object[count];
        for (int i = 0; i < count; i++) {
            params[i] = paramSuppliers[i].get();
            if (params[i] instanceof Message) {
                params[i] = ((Message) params[i]).getFormattedMessage();
            }
        }
        return entryMsg(format, params);
    }
