    public void run() {
        ActionContext.setContext(actionContext);
        //wait to start all threads at once..or try at least
        try {
            startBarrier.await();
            object = servletCache.get("org/apache/struts2/simple0.jsp");

            for (int i = 0; i < 10; i++) {
                Object object2 = servletCache.get("org/apache/struts2/simple0.jsp");
                if (object2 != object)
                    throw new RuntimeException("got different object from cache");
            }

            endBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
