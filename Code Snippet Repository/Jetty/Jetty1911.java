    public Name toCanonicalName (Name name)
    {
        Name canonicalName = name;

        if (name != null)
        {
            if (canonicalName.size() > 1)
            {
                if (canonicalName.get(0).equals(""))
                    canonicalName = canonicalName.getSuffix(1);

                if (canonicalName.get(canonicalName.size()-1).equals(""))
                    canonicalName = canonicalName.getPrefix(canonicalName.size()-1);
            }
        }

        return canonicalName;
    }
