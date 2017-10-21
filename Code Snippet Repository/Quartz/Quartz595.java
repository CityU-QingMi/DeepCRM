    private void init() {

        if (!isAlwaysLookup()) {
            Context ctx = null;
            try {
                ctx = (props != null) ? new InitialContext(props) : new InitialContext(); 

                datasource = (DataSource) ctx.lookup(url);
            } catch (Exception e) {
                getLog().error(
                        "Error looking up datasource: " + e.getMessage(), e);
            } finally {
                if (ctx != null) {
                    try { ctx.close(); } catch(Exception ignore) {}
                }
            }
        }
    }
