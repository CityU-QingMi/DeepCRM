    protected void removeExcludedCipherSuites(List<String> selected_ciphers)
    {
        for (String excludeCipherSuite : _excludeCipherSuites)
        {
            Pattern excludeCipherPattern = Pattern.compile(excludeCipherSuite);
            for (Iterator<String> i = selected_ciphers.iterator(); i.hasNext(); )
            {
                String selectedCipherSuite = i.next();
                Matcher m = excludeCipherPattern.matcher(selectedCipherSuite);
                if (m.matches())
                    i.remove();
            }
        }
    }
