    private void unBind() throws RemoteException {
        String host = resources.getRMIRegistryHost();
        // don't un-export if we're not configured to do so...
        if (host == null || host.length() == 0) {
            return;
        }

        Registry registry = LocateRegistry.getRegistry(resources
                .getRMIRegistryHost(), resources.getRMIRegistryPort());

        String bindName = resources.getRMIBindName();
        
        try {
            registry.unbind(bindName);
            UnicastRemoteObject.unexportObject(this, true);
        } catch (java.rmi.NotBoundException nbe) {
        }

        getLog().info("Scheduler un-bound from name '" + bindName + "' in RMI registry");
    }
