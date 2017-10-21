    public void selectProtocols(String[] enabledProtocols, String[] supportedProtocols)
    {
        Set<String> selected_protocols = new LinkedHashSet<>();

        // Set the starting protocols - either from the included or enabled list
        if (!_includeProtocols.isEmpty())
        {
            // Use only the supported included protocols
            for (String protocol : _includeProtocols)
            {
                if (Arrays.asList(supportedProtocols).contains(protocol))
                    selected_protocols.add(protocol);
                else
                    LOG.info("Protocol {} not supported in {}", protocol, Arrays.asList(supportedProtocols));
            }
        }
        else
            selected_protocols.addAll(Arrays.asList(enabledProtocols));

        // Remove any excluded protocols
        selected_protocols.removeAll(_excludeProtocols);

        if (selected_protocols.isEmpty())
            LOG.warn("No selected protocols from {}", Arrays.asList(supportedProtocols));

        _selectedProtocols = selected_protocols.toArray(new String[0]);
    }
