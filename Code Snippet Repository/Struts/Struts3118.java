    public void doTag(TagPluginContext ctxt) {
        
        //flag for the existence of the var attribute
        boolean hasVar = ctxt.isAttributeSpecified("var");
        
        //temp name for exception and caught
        String exceptionName = ctxt.getTemporaryVariableName();
        String caughtName = ctxt.getTemporaryVariableName();
        
        //main part to generate code
        ctxt.generateJavaSource("boolean " + caughtName + " = false;");
        ctxt.generateJavaSource("try{");
        ctxt.generateBody();
        ctxt.generateJavaSource("}");
        
        //do catch
        ctxt.generateJavaSource("catch(Throwable " + exceptionName + "){");
        
        //if the var specified, the exception object should 
        //be set to the attribute "var" defines in page scope 
        if(hasVar){
            String strVar = ctxt.getConstantAttribute("var");
            ctxt.generateJavaSource("    pageContext.setAttribute(\"" + strVar + "\", " 
                    + exceptionName + ", PageContext.PAGE_SCOPE);");
        }
        
        //whenever there's exception caught, 
        //the flag caught should be set true;
        ctxt.generateJavaSource("    " + caughtName + " = true;");
        ctxt.generateJavaSource("}");
        
        //do finally
        ctxt.generateJavaSource("finally{");
        
        //if var specified, the attribute it defines 
        //in page scope should be removed
        if(hasVar){
            String strVar = ctxt.getConstantAttribute("var");
            ctxt.generateJavaSource("    if(!" + caughtName + "){");
            ctxt.generateJavaSource("        pageContext.removeAttribute(\"" + strVar + "\", PageContext.PAGE_SCOPE);");
            ctxt.generateJavaSource("    }");
        }
        
        ctxt.generateJavaSource("}");
    }
