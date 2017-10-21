    public boolean permitAccess(String s) {

        try {
            return permitAccess(InetAddress.getByName(s).getAddress());
        } catch (UnknownHostException uke) {
            println("'" + s + "' denied because failed to resolve to an addr");

            return false;    // Resolution of candidate failed
        }
    }
