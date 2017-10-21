    public static FlumeEmbeddedManager getManager(final String name, final Agent[] agents, final Property[] properties,
                                                  int batchSize, final String dataDir) {

        if (batchSize <= 0) {
            batchSize = 1;
        }

        if ((agents == null || agents.length == 0) && (properties == null || properties.length == 0)) {
            throw new IllegalArgumentException("Either an Agent or properties are required");
        } else if (agents != null && agents.length > 0 && properties != null && properties.length > 0) {
            throw new IllegalArgumentException("Cannot configure both Agents and Properties.");
        }

        final StringBuilder sb = new StringBuilder();
        boolean first = true;

        if (agents != null && agents.length > 0) {
            sb.append(name).append('[');
            for (final Agent agent : agents) {
                if (!first) {
                    sb.append('_');
                }
                sb.append(agent.getHost()).append('-').append(agent.getPort());
                first = false;
            }
            sb.append(']');
        } else {
            String sep = Strings.EMPTY;
            sb.append(name).append('-');
            final StringBuilder props = new StringBuilder();
            for (final Property prop : properties) {
                props.append(sep);
                props.append(prop.getName()).append('=').append(prop.getValue());
                sep = "_";
            }
            sb.append(NameUtil.md5(props.toString()));
        }
        return getManager(sb.toString(), factory,
                new FactoryData(name, agents, properties, batchSize, dataDir));
    }
