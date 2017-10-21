    public void doTag(TagPluginContext ctxt) {
        
        String index = null;
        
        boolean hasVarStatus = ctxt.isAttributeSpecified("varStatus");
        if (hasVarStatus) {
            ctxt.dontUseTagPlugin();
            return;
        }
        
        hasVar = ctxt.isAttributeSpecified("var");
        hasBegin = ctxt.isAttributeSpecified("begin");
        hasEnd = ctxt.isAttributeSpecified("end");
        hasStep = ctxt.isAttributeSpecified("step");
        
        boolean hasItems = ctxt.isAttributeSpecified("items");
        if (hasItems) {
            doCollection(ctxt);
            return;
        }
        
        // We must have a begin and end attributes
        index = ctxt.getTemporaryVariableName();
        ctxt.generateJavaSource("for (int " + index + " = ");
        ctxt.generateAttribute("begin");
        ctxt.generateJavaSource("; " + index + " <= ");
        ctxt.generateAttribute("end");
        if (hasStep) {
            ctxt.generateJavaSource("; " + index + "+=");
            ctxt.generateAttribute("step");
            ctxt.generateJavaSource(") {");
        }
        else {
            ctxt.generateJavaSource("; " + index + "++) {");
        }
        
        // If var is specified and the body contains an EL, then sycn
        // the var attribute
        if (hasVar /* && ctxt.hasEL() */) {
            ctxt.generateJavaSource("_jspx_page_context.setAttribute(");
            ctxt.generateAttribute("var");
            ctxt.generateJavaSource(", String.valueOf(" + index + "));");
        }
        ctxt.generateBody();
        ctxt.generateJavaSource("}");
    }
