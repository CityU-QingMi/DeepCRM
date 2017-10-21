    protected void selectCipherSuites(String[] enabledCipherSuites, String[] supportedCipherSuites)
    {
        List<String> selected_ciphers = new ArrayList<>();

        // Set the starting ciphers - either from the included or enabled list
        if (_includeCipherSuites.isEmpty())
            selected_ciphers.addAll(Arrays.asList(enabledCipherSuites));
        else
            processIncludeCipherSuites(supportedCipherSuites, selected_ciphers);

        removeExcludedCipherSuites(selected_ciphers);

        if (selected_ciphers.isEmpty())
            LOG.warn("No supported ciphers from {}", Arrays.asList(supportedCipherSuites));

        Comparator<String> comparator = getCipherComparator();
        if (comparator != null)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Sorting selected ciphers with {}", comparator);
            Collections.sort(selected_ciphers, comparator);
        }

        _selectedCipherSuites = selected_ciphers.toArray(new String[0]);
    }
