    private boolean isValidBasicLiteralCodepoint(int codepoint)
    {
        // basic letters or digits
        if((codepoint >= 'a' && codepoint <= 'z') ||
           (codepoint >= 'A' && codepoint <= 'Z') ||
           (codepoint >= '0' && codepoint <= '9'))
        {
            return true;
        }
        
        // basic allowed symbols
        if(VARIABLE_SYMBOLS.indexOf(codepoint) >= 0)
        {
            return true; // valid simple value
        }
        
        // basic reserved symbols
        if(VARIABLE_RESERVED.indexOf(codepoint) >= 0)
        {
            LOG.warn("Detected URI Template reserved symbol [{}] in path spec \"{}\"",(char)codepoint,pathSpec);
            return false; // valid simple value
        }

        return false;
    }
