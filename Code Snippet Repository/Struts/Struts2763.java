    private void generateTagHandlerPostamble(TagInfo tagInfo) {
        out.popIndent();

        // Have to catch Throwable because a classic tag handler
        // helper method is declared to throw Throwable.
        out.printil("} catch( Throwable t ) {");
        out.pushIndent();
        out.printil("if( t instanceof SkipPageException )");
        out.printil("    throw (SkipPageException) t;");
        out.printil("if( t instanceof java.io.IOException )");
        out.printil("    throw (java.io.IOException) t;");
        out.printil("if( t instanceof IllegalStateException )");
        out.printil("    throw (IllegalStateException) t;");
        out.printil("if( t instanceof JspException )");
        out.printil("    throw (JspException) t;");
        out.printil("throw new JspException(t);");
        out.popIndent();
        out.printil("} finally {");
        out.pushIndent();
        
        // handle restoring VariableMapper
        TagAttributeInfo[] attrInfos = tagInfo.getAttributes();
        for (int i = 0; i < attrInfos.length; i++) {
            if (attrInfos[i].isDeferredMethod() || attrInfos[i].isDeferredValue()) {
                out.printin("_el_variablemapper.setVariable(");
                out.print(quote(attrInfos[i].getName()));
                out.print(",_el_ve");
                out.print(i);
                out.println(");");
            }
        }
        
        // restore nested JspContext on ELContext
        out.printil("jspContext.getELContext().putContext(JspContext.class,super.getJspContext());");
        
        out.printil("((org.apache.struts2.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();");
        if (isPoolingEnabled && !tagHandlerPoolNames.isEmpty()) {
            out.printil("_jspDestroy();");
        }
        out.popIndent();
        out.printil("}");

        // Close the doTag method
        out.popIndent();
        out.printil("}");

        // Generated methods, helper classes, etc.
        genCommonPostamble();
    }
