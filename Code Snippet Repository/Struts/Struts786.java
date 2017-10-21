    static void lock(Object o, ActionInvocation invocation) throws Exception {
        synchronized (o) {
            int count = 3;
            Object previous;
            while ((previous = locks.get(o)) != null) {
                if (previous == invocation) {
                    return;
                }
                if (count-- <= 0) {
                    locks.remove(o);
                    o.notify();

                    throw new StrutsException("Deadlock in session lock");
                }
                o.wait(10000);
            }
            locks.put(o, invocation);
        }
    }
