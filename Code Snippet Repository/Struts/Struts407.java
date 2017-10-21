    @Override
    public synchronized Object get(int index) {
        while (index >= this.size()) {
            try {
                this.add(getObjectFactory().buildBean(clazz, ActionContext.getContext().getContextMap()));
            } catch (Exception e) {
                throw new XWorkException(e);
            }
        }

        return super.get(index);
    }
