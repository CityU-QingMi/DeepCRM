    private static Agent[] getAgents(Agent[] agents, final String hosts) {
        if (agents == null || agents.length == 0) {
            if (hosts != null && !hosts.isEmpty()) {
                LOGGER.debug("Parsing agents from hosts parameter");
                final String[] hostports = hosts.split(",");
                agents = new Agent[hostports.length];
                for(int i = 0; i < hostports.length; ++i) {
                    final String[] h = hostports[i].split(":");
                    agents[i] = Agent.createAgent(h[0], h.length > 1 ? h[1] : null);
                }
            } else {
                LOGGER.debug("No agents provided, using defaults");
                agents = new Agent[] {Agent.createAgent(null, null)};
            }
        }

        LOGGER.debug("Using agents {}", agents);
        return agents;
    }
