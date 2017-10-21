    public boolean acknowledgeLicenses() throws IOException
    {
        StartLog.debug("Requesting License Acknowledgement");
        
        if (!hasLicenses())
        {
            return true;
        }

        System.err.printf("%nALERT: There are enabled module(s) with licenses.%n");
        System.err.printf("The following %d module(s):%n", licenseMap.size());
        System.err.printf(" + contains software not provided by the Eclipse Foundation!%n");
        System.err.printf(" + contains software not covered by the Eclipse Public License!%n");
        System.err.printf(" + has not been audited for compliance with its license%n");

        for (String key : licenseMap.keySet())
        {
            System.err.printf("%n Module: %s%n",key);
            for (String line : licenseMap.get(key))
            {
                System.err.printf("  + %s%n",line);
            }
        }

        boolean licenseAck = false;

        String propBasedAckValue = System.getProperty(PROP_ACK_LICENSES);
        if (propBasedAckValue != null)
        {
            StartLog.log("TESTING MODE","Programmatic ACK - %s=%s",PROP_ACK_LICENSES,propBasedAckValue);
            licenseAck = Boolean.parseBoolean(propBasedAckValue);
        }
        else
        {
            if (Boolean.getBoolean("org.eclipse.jetty.start.testing"))
            {
                throw new RuntimeException("Test Configuration Missing - Pre-specify answer to (" + PROP_ACK_LICENSES + ") in test case");
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.err.printf("%nProceed (y/N)? ");
            String response = input.readLine();

            licenseAck = (Utils.isNotBlank(response) && response.toLowerCase(Locale.ENGLISH).startsWith("y"));
        }

        return licenseAck;
    }
