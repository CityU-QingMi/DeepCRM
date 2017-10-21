    protected boolean subnetMatch(String subnetAddress, String address)
    {
        Matcher cidrMatcher = CIDR_PATTERN.matcher(subnetAddress);
        if (!cidrMatcher.matches())
            return false;

        String subnet = cidrMatcher.group(1);
        int prefix;
        try
        {
            prefix = Integer.parseInt(cidrMatcher.group(2));
        }
        catch (NumberFormatException x)
        {
            LOG.info("Ignoring malformed CIDR address {}", subnetAddress);
            return false;
        }

        byte[] subnetBytes = addressToBytes(subnet);
        if (subnetBytes == null)
        {
            LOG.info("Ignoring malformed CIDR address {}", subnetAddress);
            return false;
        }
        byte[] addressBytes = addressToBytes(address);
        if (addressBytes == null)
        {
            LOG.info("Ignoring malformed remote address {}", address);
            return false;
        }

        // Comparing IPv4 with IPv6 ?
        int length = subnetBytes.length;
        if (length != addressBytes.length)
            return false;

        byte[] mask = prefixToBytes(prefix, length);

        for (int i = 0; i < length; ++i)
        {
            if ((subnetBytes[i] & mask[i]) != (addressBytes[i] & mask[i]))
                return false;
        }

        return true;
    }
