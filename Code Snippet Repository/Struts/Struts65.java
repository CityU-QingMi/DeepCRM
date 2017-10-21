    public static LinkedList<String> getChainHistory() {
        LinkedList<String> chainHistory = (LinkedList<String>) ActionContext.getContext().get(CHAIN_HISTORY);
        //  Add if not exists
        if (chainHistory == null) {
            chainHistory = new LinkedList<>();
            ActionContext.getContext().put(CHAIN_HISTORY, chainHistory);
        }

        return chainHistory;
    }
