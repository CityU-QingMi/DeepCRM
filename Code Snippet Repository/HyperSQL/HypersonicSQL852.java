    public synchronized void setAttribute(int id, Object object) {

        switch (id) {

            case SessionInterface.INFO_AUTOCOMMIT : {
                boolean value = ((Boolean) object).booleanValue();

                this.setAutoCommit(value);

                break;
            }
            case SessionInterface.INFO_CONNECTION_READONLY : {
                boolean value = ((Boolean) object).booleanValue();

                this.setReadOnlyDefault(value);

                break;
            }
            case SessionInterface.INFO_ISOLATION : {
                int value = ((Integer) object).intValue();

                this.setIsolationDefault(value);

                break;
            }
            case SessionInterface.INFO_CATALOG : {
                String value = ((String) object);

                this.setCatalog(value);
            }
        }
    }
