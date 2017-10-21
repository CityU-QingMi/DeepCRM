    public void closeFully() {

        try {
            close();
        } catch (Throwable t) {

            //
        }

        try {
            if (sessionProxy != null) {
                sessionProxy.close();

                sessionProxy = null;
            }
        } catch (Throwable t) {

            //
        }
    }
