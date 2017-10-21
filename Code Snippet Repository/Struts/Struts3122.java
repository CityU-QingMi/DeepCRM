    public void doTag(TagPluginContext ctxt) {
        // Get the parent context to determine if this is the first <c:when>
        TagPluginContext parentContext = ctxt.getParentContext();
        if (parentContext == null) {
            ctxt.dontUseTagPlugin();
            return;
        }
        
        if ("true".equals(parentContext.getPluginAttribute("hasBeenHere"))) {
            ctxt.generateJavaSource("} else if(");
            // See comment below for the reason we generate the extra "}" here.
        }
        else {
            ctxt.generateJavaSource("if(");
            parentContext.setPluginAttribute("hasBeenHere", "true");
        }
        ctxt.generateAttribute("test");
        ctxt.generateJavaSource("){");
        ctxt.generateBody();
        
        // We don't generate the closing "}" for the "if" here because there
        // may be whitespaces in between <c:when>'s.  Instead we delay
        // generating it until the next <c:when> or <c:otherwise> or
        // <c:choose>
    }
