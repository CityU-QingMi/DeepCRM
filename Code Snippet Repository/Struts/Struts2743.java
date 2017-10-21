        public void visit(Node.GetProperty n) throws JasperException {
            String name = n.getTextAttribute("name");
            String property = n.getTextAttribute("property");

            n.setBeginJavaLine(out.getJavaLine());

            if (beanInfo.checkVariable(name)) {
                // Bean is defined using useBean, introspect at compile time
                Class bean = beanInfo.getBeanType(name);
                String beanName = JspUtil.getCanonicalName(bean);
                java.lang.reflect.Method meth = JspRuntimeLibrary
                        .getReadMethod(bean, property);
                String methodName = meth.getName();
                out
                        .printil("out.write(org.apache.struts2.jasper.runtime.JspRuntimeLibrary.toString("
                                + "((("
                                + beanName
                                + ")_jspx_page_context.findAttribute("
                                + "\""
                                + name + "\"))." + methodName + "())));");
            } else {
                // The object could be a custom action with an associated
                // VariableInfo entry for this name.
                // Get the class name and then introspect at runtime.
                out
                        .printil("out.write(org.apache.struts2.jasper.runtime.JspRuntimeLibrary.toString"
                                + "(org.apache.struts2.jasper.runtime.JspRuntimeLibrary.handleGetProperty"
                                + "(_jspx_page_context.getAttribute(\""
                                + name
                                + "\", PageContext.PAGE_SCOPE), \""
                                + property
                                + "\")));");
            }

            n.setEndJavaLine(out.getJavaLine());
        }
