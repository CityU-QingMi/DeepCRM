    @Override
    public Collection<AgentMetadataEntity> getAgentsMetadata(UriInfo info) {
        // validator.validateSafe(info);

        AgentMetadataEntity ame = new AgentMetadataEntity();
        ame.setAgentId(AgentEntity.EMBEDDED_AGENT_ID);
        // TODO: I imagine there is some specific ehcache naming detail we can
        // discover to build up a more descriptive name.
        // If this ends up being a static value then make it a static member.
        ame.setAgencyOf("Quartz");
        // Set the version from this package
        ame.setVersion(this.getClass().getPackage().getImplementationVersion());
        ame.setAvailable(true);
        return Collections.singleton(ame);
    }
